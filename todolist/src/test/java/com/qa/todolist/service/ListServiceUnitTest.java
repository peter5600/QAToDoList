package com.qa.todolist.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.qa.todolist.data.model.Lists;
import com.qa.todolist.data.repository.ListRepository;
import com.qa.todolist.dto.ListDTO;
import com.qa.todolist.mappers.ListMapper;
import com.qa.todolist.services.ListService;

@ExtendWith(MockitoExtension.class)//setup testing since spring isnt running this is required 
//could also do @SpringBootTest and also @MockBean and @AutoWired the values but this works as well
public class ListServiceUnitTest {

	@InjectMocks
	private ListService listService;
	
	@Mock
	private ListRepository listRepo;
	
	@Mock 
	private ListMapper listMapper;
	
	//make values to test against
	
	private List<Lists> lists;
	private List<ListDTO> listDTOs;
	
	private Lists validList;
	private ListDTO validListDTO;
	
	@BeforeEach
	public void initialSetup() {
		lists = new ArrayList<Lists>();
		listDTOs = new ArrayList<ListDTO>();
		
		validList = new Lists(1, "Peters list","19-03-2021 09:59");
		validListDTO = new ListDTO(1l, "Peters list", "19-03-2021 09:59");
		
		
	}
	
	@Test
	public void addListTest() {
		when(listRepo.save(Mockito.any(Lists.class))).thenReturn(validList);
		when(listMapper.mapToDTO(Mockito.any(Lists.class))).thenReturn(validListDTO);
		
		assertThat(validListDTO).isEqualTo(listService.addList(validList));
		
		verify(listRepo, times(1)).save(Mockito.any(Lists.class));
		verify(listMapper, times(1)).mapToDTO(Mockito.any(Lists.class));
	}
	@Test
	public void getListByIDTest() {
		when(listRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(validList));
		when(listMapper.mapToDTO(Mockito.any(Lists.class))).thenReturn(validListDTO);
		
		assertThat(validListDTO).isEqualTo(listService.getListByID(Mockito.anyInt()));
		
		verify(listRepo, times(1)).findById(Mockito.anyInt());
		verify(listMapper, times(1)).mapToDTO(Mockito.any(Lists.class));
	}
	@Test
	public void getAllListsTest() {
		lists.add(validList);
		listDTOs.add(validListDTO);
		
		when(listRepo.findAll()).thenReturn(lists);
		when(listMapper.mapToListDTOFromList(lists)).thenReturn(listDTOs);
		
		assertThat(listDTOs).isEqualTo(listService.getAllLists());
		
		verify(listRepo,times(1)).findAll();
		verify(listMapper,times(1)).mapToListDTOFromList(lists);
	}
	@Test
	public void deleteListByIDTest() {
		listService.addList(validList);
		System.out.println(validList.toString());
		when(listRepo.existsById(1)).thenReturn(true,false);
		//must call the whens togther otherwise it causes an issue
		//deletebyid dosent return anything so its not included just mocking the output
		
		
		assertThat(true).isEqualTo(listService.DeleteListById(validList.getId()));
		
		verify(listRepo, times(2)).existsById(Mockito.anyInt());
	}
	@Test
	public void modifyListTest() {
		when(listRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(validList));
		when(listRepo.save(Mockito.any(Lists.class))).thenReturn(validList);
		when(listMapper.mapToDTO(Mockito.any(Lists.class))).thenReturn(validListDTO);
		
		assertThat(validListDTO).isEqualTo(listService.modifyList(1, validList));
		
		verify(listRepo, times(1)).findById(Mockito.anyInt());
		verify(listRepo, times(1)).save(Mockito.any(Lists.class));
		verify(listMapper, times(1)).mapToDTO(Mockito.any(Lists.class));
	}
}
