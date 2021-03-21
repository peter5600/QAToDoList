package com.qa.todolist.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.todolist.data.model.Lists;
import com.qa.todolist.data.repository.ListRepository;
import com.qa.todolist.dto.ListDTO;
import com.qa.todolist.mappers.ListMapper;
import com.qa.todolist.services.ListService;

@SpringBootTest
public class ListServiceIntegrationTest {
	
	@Autowired
	private ListService listService;
	
	@Autowired
	private ListMapper listMapper;
	
	@Autowired
	private ListRepository listRepo;
	
	private Lists validList;
	private ListDTO validListDTO;
	
	private List<ListDTO> listOfListDTOs;
	
	
	
	@BeforeEach
	public void init() {
		listOfListDTOs = new ArrayList<ListDTO>();
		
		validList = new Lists(1,"Peters list","19-03-2021 09:58");
		listRepo.deleteAll();
		validList = listRepo.save(validList);
		validListDTO = listMapper.mapToDTO(validList);
	}
	
	@Test
	public void addListTest() {
		validList = new Lists(validList.getId() + 1,"Peters list","19-03-2021 09:58");
		validListDTO = listMapper.mapToDTO(validList);
		//cant use validList because this wants to save it and validList is saved above so instead make a new one 
		//and increase id by 1 so that i know it will defintley be the one after the one just saved
		ListDTO returnedList = listService.addList(validList);
		assertThat(returnedList).isEqualTo(validListDTO);
	}
	
	@Test
	public void getListByIDTest() {
		ListDTO returnedList = listService.getListByID(validList.getId());
		assertThat(returnedList).isEqualTo(validListDTO);
	}
	
	@Test
	public void getAllListsTest() {
		listOfListDTOs.add(validListDTO);
		
		List<ListDTO> returnedLists = listService.getAllLists();
		assertThat(returnedLists).isEqualTo(listOfListDTOs);
	}
	
	@Test
	public void DeleteListByIDTest() {
		Boolean wasDeleted = listService.DeleteListById(validList.getId());
		assertThat(wasDeleted).isEqualTo(true);
	}
	
	@Test
	public void modifyListTest() {
		ListDTO modifiedList = listService.modifyList(validList.getId(), validList);
		assertThat(modifiedList).isEqualTo(validListDTO);
	}
}
