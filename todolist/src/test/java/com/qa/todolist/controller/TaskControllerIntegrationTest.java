package com.qa.todolist.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
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
import com.qa.todolist.data.model.Task;
import com.qa.todolist.dto.TaskDTO;
import com.qa.todolist.mappers.TaskMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:test-schema.sql","classpath:test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class TaskControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private TaskMapper taskMapper;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private Task validTask = new Task(1,new Lists(1,"Peters list","19-03-2021 09:58"),"This is my task",false,"19-03-2021 09:58");
	private TaskDTO validTaskDTO = new TaskDTO(1,"This is my task",false,"19-03-2021 09:58");
	private List<TaskDTO> listOfDTOs = new ArrayList<TaskDTO>();
	
	@Test
	public void getTaskByListIDTest() throws Exception {
		listOfDTOs.add(validTaskDTO);
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/task/1");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(listOfDTOs));
		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	@Test
	public void modifyTaskByIDTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PATCH, "/task/1");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(validTaskDTO));
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(validTaskDTO));
		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	@Test
	public void completeTaskByIDTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PATCH, "/task/1/completed");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("true");
		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	@Test
	public void deleteTaskByIDTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/task/1");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("true");
		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	@Test
	public void addTaskByIDTest() throws Exception {
		Task newValidTask = new Task(1,new Lists(2,"Peters list","19-03-2021 09:58"),"This is my second task",false,"19-03-2021 09:59");
		TaskDTO newValidTaskDTO = taskMapper.mapToDTO(newValidTask);
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/task");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		mockRequest.contentType(MediaType.APPLICATION_JSON);//set the data im sending to json
		mockRequest.content(objectMapper.writeValueAsString(newValidTaskDTO));
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(newValidTaskDTO));
		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	
	
	
	
}
