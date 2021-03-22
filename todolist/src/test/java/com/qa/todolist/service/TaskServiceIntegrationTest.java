package com.qa.todolist.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.todolist.data.model.Lists;
import com.qa.todolist.data.model.Task;
import com.qa.todolist.data.repository.ListRepository;
import com.qa.todolist.data.repository.TaskRepository;
import com.qa.todolist.dto.TaskDTO;
import com.qa.todolist.mappers.TaskMapper;
import com.qa.todolist.services.TaskService;

@SpringBootTest//dosent start the server but it does start the h2 db so i can get a real look at how these will work together
public class TaskServiceIntegrationTest {

	@Autowired 
	private TaskService taskService;//not mocking in integration testing wiring in the real object
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private TaskMapper taskMapper;
	
	@Autowired
	private ListRepository listRepo;
	
	private List<TaskDTO> taskDTOs;
	
	private Task validTask;
	private TaskDTO validTaskDTO;
	
	private Lists validList;
	
	
	@BeforeEach
	public void init() {
		taskDTOs = new ArrayList<TaskDTO>();
		
		validList = new Lists(1,"Peters list","19-03-2021 09:58");
		listRepo.deleteAll();
		validList = listRepo.save(validList);//need this because task is looking for a list that dosent exist
		//the reason i pass validList is because when a record is deleted the id is still taken
		//so i can't keep passing id 1 i need to pass the next id
		validTask = new Task(1, validList,"Complete Unit tests", false,"19-03-2021 09:59");
		validTaskDTO = new TaskDTO(1,"Complete Unit tests", false,"19-03-2021 09:59");
		
		taskRepo.deleteAll();//were using the real system so i need to simulate 
		//pre poping the db so empty add
		validTask = taskRepo.save(validTask);
		//again the id changes need to adapt for every test
		validTaskDTO = taskMapper.mapToDTO(validTask);
		
		/*tasks.add(validTask);
		taskDTOs.add(validTaskDTO);*/ // add this in the tests that need it if i have 100 tests that might need 
		//diffrent params to be added it should be specialised for that test
		
		
	}
	//just run the function and assertThat it matches what i expect
	@Test
	public void returnAllTasksFromListByIDTest() {
		taskDTOs.add(validTaskDTO);
		//added for this test
		
		List<TaskDTO> returnLists = taskService.returnAllTasksFromListByID(validList.getId());//1 is the id passed above
		assertThat(returnLists).isEqualTo(taskDTOs);
	}
	
	@Test
	public void modifyTaskTest() {
		TaskDTO returnedTask = taskService.modifyTask(validTask.getId(), validTask);
		assertThat(returnedTask).isEqualTo(validTaskDTO);
	}
	
	@Test
	public void completeTaskTest() {
		Boolean completed = taskService.completeTask(validTask.getId());
		assertThat(completed).isTrue();
	}
	
	@Test
	public void deleteTaskTest() {
		Boolean deleted = taskService.deleteTask(validTask.getId());
		assertThat(deleted).isEqualTo(true);
	}
	
	@Test
	public void addTaskTest() {
		TaskDTO addedTask = taskService.addTask(validTask);
		assertThat(addedTask).isEqualTo(validTaskDTO);
	}
}

