package com.qa.todolist.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.qa.todolist.data.model.Lists;
import com.qa.todolist.data.model.Task;
import com.qa.todolist.data.repository.TaskRepository;
import com.qa.todolist.dto.TaskDTO;
import com.qa.todolist.mappers.TaskMapper;
import com.qa.todolist.services.TaskService;



@ExtendWith(MockitoExtension.class)
public class TaskServiceUnitTest {

	@InjectMocks
	private TaskService taskService;
	
	@Mock
	private TaskRepository taskRepo;
	
	@Mock 
	private TaskMapper taskMapper;
	
	private List<Task> tasks;
	private List<TaskDTO> taskDTOs;
	
	private Task validTask;
	private TaskDTO validTaskDTO;
	
	@BeforeEach
	public void init() {
		tasks = new ArrayList<Task>();
		taskDTOs = new ArrayList<TaskDTO>();
		
		validTask = new Task(1, new Lists(1,"Peters list","19-03-2021 09:58"),"Complete Unit tests", false,"19-03-2021 09:59");
		validTaskDTO = new TaskDTO(1l,"Complete Unit tests", false,"19-03-2021 09:59");
	}
	
	@Test
	public void returnAllTasksTest() {
		tasks.add(validTask);
		taskDTOs.add(validTaskDTO);
		//taskService.
		when(taskRepo.findAllTasksFromListID(Mockito.anyInt())).thenReturn(tasks);
		when(taskMapper.mapToTaskDTOFromList(tasks)).thenReturn(taskDTOs);
		
		assertThat(taskDTOs).isEqualTo(taskService.returnAllTasksFromListByID(1));
	}
	
	@Test 
	public void modifyTaskTest() {
		
	}
	
	@Test
	public void completeTaskTest() {
		
	}
	
	@Test
	public void deleteTaskTest() {
		
	}
}
