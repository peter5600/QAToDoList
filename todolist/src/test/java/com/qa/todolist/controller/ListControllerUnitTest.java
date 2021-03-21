package com.qa.todolist.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.todolist.data.model.Lists;
import com.qa.todolist.dto.ListDTO;
import com.qa.todolist.services.ListService;

@WebMvcTest(ListController.class)
//loads the beans that i need and the web server so i can properley test this
public class ListControllerUnitTest {
	
	//Not that complex just mock the function like i would with mockito and then create the reponse entity 
	//and the assertThat and verify
	
	@Autowired
	private ListController listController;
	
	@MockBean//Not Mock but MockBean since these are being treated as beans now
	private ListService listService;
	
	
	private List<ListDTO> listOfListDTOs;
	
	private Lists validList;
	private ListDTO validListDTO;
	
	@BeforeEach
	public void init() {
		validList = new Lists(1,"Peters list","19-03-2021 09:58");
		validListDTO = new ListDTO(1,"Peters list","19-03-2021 09:58");//could also make use list mapper
		//but to avoid an extra import make the obj myself
		
		listOfListDTOs = new ArrayList<ListDTO>();
		
		
	}
	
	@Test
	public void getListByListIDTest() {
		when(listService.getListByID(Mockito.anyInt())).thenReturn(validListDTO);
		
		ResponseEntity<ListDTO> expected = new ResponseEntity<ListDTO>(validListDTO, HttpStatus.FOUND);
		
		assertThat(expected).isEqualTo(listController.getListByListID(Mockito.anyInt()));
		
		verify(listService, times(1)).getListByID(Mockito.anyInt());
	}
	
	@Test
	public void getListOfLists() {
		listOfListDTOs.add(validListDTO);
		when(listService.getAllLists()).thenReturn(listOfListDTOs);
		
		ResponseEntity<List<ListDTO>> expected = new ResponseEntity<List<ListDTO>>(listOfListDTOs, HttpStatus.OK);
		
		assertThat(expected).isEqualTo(listController.getListOfLists());
		
		verify(listService, times(1)).getAllLists();
	}
	
	@Test
	public void modifyListByIDTest(){
		when(listService.modifyList(validList.getId(), validList)).thenReturn(validListDTO);
		
		
		ResponseEntity<ListDTO> expected = new ResponseEntity<ListDTO>(validListDTO, HttpStatus.OK);
		
		assertThat(expected).isEqualTo(listController.modifyListByID(validList.getId(), validList));
		
		verify(listService, times(1)).modifyList(validList.getId(), validList);
	}
	
	@Test
	public void deleteListByIDTest() {
		when(listService.DeleteListById(Mockito.anyInt())).thenReturn(true);
		
		ResponseEntity<Boolean> expected = new ResponseEntity<Boolean>(true, HttpStatus.FOUND);
		
		assertThat(expected).isEqualTo(listController.deleteListById(Mockito.anyInt()));
		
		verify(listService, times(1)).DeleteListById(Mockito.anyInt());
	}
	
	@Test
	public void addListTest() {
		when(listService.addList(validList)).thenReturn(validListDTO);
		
		ResponseEntity<ListDTO> expected = new ResponseEntity<ListDTO>(validListDTO, HttpStatus.CREATED);
		
		assertThat(expected).isEqualTo(listController.addList(validList));
		
		verify(listService, times(1)).addList(validList);
	}
	
}
