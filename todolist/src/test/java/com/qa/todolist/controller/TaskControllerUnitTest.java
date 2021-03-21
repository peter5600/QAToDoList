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
import com.qa.todolist.data.model.Task;
import com.qa.todolist.dto.TaskDTO;
import com.qa.todolist.services.TaskService;

@WebMvcTest(TaskController.class)
public class TaskControllerUnitTest {

	@Autowired
	private TaskController taskController;
	
	@MockBean
	private TaskService taskService;
	
	private List<TaskDTO> listOfTaskDTOs;
	
	private Task validTask;
	private TaskDTO validTaskDTO;
	
	@BeforeEach
	public void init() {
		listOfTaskDTOs = new ArrayList<TaskDTO>();
		
		validTask = new Task(1, new Lists(1,"Peters list","19-03-2021 09:58"),"Complete Unit tests", false,"19-03-2021 09:59");
		validTaskDTO = new TaskDTO(1,"Complete Unit tests", false,"19-03-2021 09:59");
		//dont need to make a list because im comparing the http request results not the values within the controllers
	}
	
	@Test
	public void getTaskByListIDTest() {
		when(taskService.returnAllTasksFromListByID(Mockito.anyInt())).thenReturn(listOfTaskDTOs);
		
		ResponseEntity<List<TaskDTO>> expected = new ResponseEntity<List<TaskDTO>>(listOfTaskDTOs, HttpStatus.OK);
		assertThat(expected).isEqualTo(taskController.getTaskByListID(validTask.getId()));
		
		verify(taskService,times(1)).returnAllTasksFromListByID(Mockito.anyInt());
	}
	
	@Test
	public void modifyTaskByIDTest() {
		when(taskService.modifyTask(Mockito.anyInt(), Mockito.any(Task.class))).thenReturn(validTaskDTO);
		
		ResponseEntity<TaskDTO> expected = new ResponseEntity<TaskDTO>(validTaskDTO, HttpStatus.OK);
		
		assertThat(expected).isEqualTo(taskController.modifyTaskByID(validTask.getId(), validTask));
		
		verify(taskService, times(1)).modifyTask(Mockito.anyInt(), Mockito.any(Task.class));
	}
	
	@Test
	public void completeTaskByIDTest() {
		when(taskService.completeTask(Mockito.anyInt())).thenReturn(true);
		ResponseEntity<Boolean> expected = new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
		assertThat(expected).isEqualTo(taskController.completedTaskByID(Mockito.anyInt()));
		
		verify(taskService, times(1)).completeTask(Mockito.anyInt());
	}
	
	@Test
	public void deleteTaskByIDTest() {
		when(taskService.deleteTask(Mockito.anyInt())).thenReturn(true);
		ResponseEntity<Boolean> expected = new ResponseEntity<Boolean>(true, HttpStatus.OK);
		assertThat(expected).isEqualTo(taskController.deleteTaskById(Mockito.anyInt()));
		verify(taskService, times(1)).deleteTask(Mockito.anyInt());
	}
	
	@Test
	public void addTaskTest() {
		when(taskService.addTask(Mockito.any(Task.class))).thenReturn(validTaskDTO);
		ResponseEntity<TaskDTO> expected = new ResponseEntity<TaskDTO>(validTaskDTO, HttpStatus.OK);
		assertThat(expected).isEqualTo(taskController.addTask(validTask));
		verify(taskService, times(1)).addTask(Mockito.any(Task.class));
	}
}
