package com.qa.todolist.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.todolist.data.model.Lists;
import com.qa.todolist.dto.ListDTO;
import com.qa.todolist.mappers.ListMapper;

//this is the integration test cant mock it need to actually simulate the api
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//classpath is resources folder
@Sql(scripts = {"classpath:test-schema.sql","classpath:test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//run the scripts in the resources folder before the test method this is because this isnt mocked the data will actually be stored
//so need to make sure 
//to get these sql files i can either make them myself or have spring generate them for me
//jpa 2.1 and above will gen sql for me
//then use the properties i have put in the app.prop files and on launch it will generate the schema
public class ListControllerIntegrationTest {

	// alter table tasks add constraint FKdtss7ab4sx4oagfqyacono43a foreign key (fk_list_id) references tbList on delete cascade

	@Autowired
	private MockMvc mvc;//get the mvc object which is used to simulate sending requests and catches what would have been sent
	
	@Autowired
	private ListMapper listMapper;
	
	@Autowired
	private ObjectMapper objectMapper;//this is used to convert objects into json
	
	private Lists validList = new Lists(1,"Peters list","19-03-2021 09:58");
	private ListDTO validListDTO = new ListDTO(1,"Peters list","19-03-2021 09:58");
	private List<ListDTO> listOfDTOs = new ArrayList<ListDTO>();
	
	//no before each the closest is the SQL annotation which resets the db
	
	//general structure
	//make a MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "PATH i.e /list/1 not list/{id}");
	//then what it should accept mockRequest.accept(MediaType.APPLICATION_JSON);
	//then based on the endpoint i might need to say what the query param is but mine are all passed in the URL
	//then make a resultmatcher which is an object that makes sure that teh request is the way i expect
	//ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk(); so request returned OK
	//then the content is passed ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(duckDTO));
	//then mvc perform im guessing has a builtin assertThat or similar that will check whether the test passed 
	//mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	
	
	@Test
	public void getListByIDTest() throws Exception {
		//first make a request builder
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/list/1");//make sure id matches the list above and the data in the @sql annotation
		//what data type does it accept
		mockRequest.accept(MediaType.APPLICATION_JSON);
		//what status is it
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isFound();//mine is found
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()//write the data as json im writing the validListDTO which is also stored in the db
				.json(objectMapper.writeValueAsString(validListDTO));//this might fail and throw a JsonProcessingException
		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);//throws exception
		//this reads use enpoint /list/1 and im expecting back Found status and the data in validListDTO as JSON
	}
	
	@Test
	public void getListOfListsTest() throws Exception {
		listOfDTOs.add(validListDTO);
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/list");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(listOfDTOs));
		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	@Test
	public void modifyListTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PATCH, "/list/1");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		mockRequest.contentType(MediaType.APPLICATION_JSON);//set the data im sending to json
		mockRequest.content(objectMapper.writeValueAsString(validListDTO));//write the data as a string this will be sent to the db
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(validListDTO));
		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	@Test
	public void deleteListTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/list/1");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isFound();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("true");//need to return bool this should return a string bool true
		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	@Test
	public void addListTest() throws Exception {
		Lists newValidList = new Lists(2,"Peters list");//im not going to know the id but the system needs it for the test
		//even though i wont know the id it will be generated so for this test im passing but in reality i wouldnt pass the id
		ListDTO newValidListDTO = listMapper.mapToDTO(newValidList);
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/list");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		mockRequest.contentType(MediaType.APPLICATION_JSON);//set the data im sending to json
		mockRequest.content(objectMapper.writeValueAsString(newValidList));//write the data as a string this will be sent to the db
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(newValidListDTO));
		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
}
