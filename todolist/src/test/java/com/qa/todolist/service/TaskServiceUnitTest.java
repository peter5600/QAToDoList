package com.qa.todolist.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.qa.todolist.data.model.Task;
import com.qa.todolist.data.repository.TaskRepository;
import com.qa.todolist.dto.TaskDTO;
import com.qa.todolist.exceptions.TaskNotFoundException;
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
		validTaskDTO = new TaskDTO(1,"Complete Unit tests", false,"19-03-2021 09:59");
	}
	
	@Test
	public void returnAllTasksTest() {
		tasks.add(validTask);
		taskDTOs.add(validTaskDTO);
		taskService.addTask(validTask);
		when(taskRepo.findAllTasksFromListID(Mockito.anyInt())).thenReturn(tasks);
		when(taskMapper.mapToTaskDTOFromList(tasks)).thenReturn(taskDTOs);
		
		assertThat(taskDTOs).isEqualTo(taskService.returnAllTasksFromListByID(1));
		
		verify(taskRepo, times(1)).findAllTasksFromListID(Mockito.anyInt());
		verify(taskMapper, times(1)).mapToTaskDTOFromList(tasks);
	}
	
	@Test 
	public void modifyTaskTest() {
		when(taskRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(validTask));
		when(taskRepo.save(Mockito.any(Task.class))).thenReturn(validTask);
		when(taskMapper.mapToDTO(Mockito.any(Task.class))).thenReturn(validTaskDTO);
		
		assertThat(validTaskDTO).isEqualTo(taskService.modifyTask(1, validTask));
		
		verify(taskRepo, times(1)).findById(Mockito.anyInt());
		verify(taskRepo,times(1)).save(Mockito.any(Task.class));
		verify(taskMapper, times(1)).mapToDTO(Mockito.any(Task.class));
	}
	
	@Test
	public void completeTaskTest() {
		when(taskRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(validTask));
		when(taskRepo.save(Mockito.any(Task.class))).thenReturn(validTask);
		
		assertThat(true).isEqualTo(taskService.completeTask(1));
		
		verify(taskRepo, times(1)).findById(Mockito.anyInt());
		verify(taskRepo,times(1)).save(Mockito.any(Task.class));
	}
	
	@Test
	public void deleteTaskTest() {
		when(taskRepo.existsById(Mockito.anyInt())).thenReturn(true, false);
		
		assertThat(true).isEqualTo(taskService.deleteTask(1));
		
		verify(taskRepo, times(2)).existsById(Mockito.anyInt());
	}
	
}
