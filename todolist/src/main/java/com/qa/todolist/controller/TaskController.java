package com.qa.todolist.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.todolist.data.model.Task;
import com.qa.todolist.dto.TaskDTO;
import com.qa.todolist.services.TaskService;

@RestController
@RequestMapping(path = "/task")
@CrossOrigin // enables cross origin allowing other services like my front end to use this api
//if i get more time then i should look at ways to a list of services that can use the api to secure it
public class TaskController {

	
	private TaskService taskService;
	
	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	//view tasks
	@GetMapping("/{id}")//list id
	public ResponseEntity<List<TaskDTO>> getTaskByListID(@PathVariable("id") Integer id){
		List<TaskDTO> TasksFromList = taskService.returnAllTasksFromListByID(id);
		return new ResponseEntity<List<TaskDTO>>(TasksFromList, HttpStatus.OK);
		
	}
	
	//modify tasks
	@PatchMapping("/{id}")
	public ResponseEntity<TaskDTO> modifyTaskByID(@PathVariable("id") Integer id, @Valid @RequestBody Task task){
		TaskDTO modifiedTask = taskService.modifyTask(id, task);
		return new ResponseEntity<TaskDTO>(modifiedTask, HttpStatus.OK);
	}
	
	//completed
	@PatchMapping("/{id}/completed")
	public ResponseEntity<Boolean> completedTaskByID(@PathVariable("id") Integer id){
		Boolean completed = taskService.completeTask(id);
		return new ResponseEntity<Boolean>(completed, HttpStatus.OK);
	}
	//delete tasks
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteTaskById(@PathVariable("id") Integer id){
		Boolean deleted = taskService.deleteTask(id);
		return new ResponseEntity<Boolean>(deleted, HttpStatus.OK);
	};
	//add tasks
	@PostMapping
	public ResponseEntity<TaskDTO> addTask(@RequestBody Task task){
		TaskDTO taskAdded = taskService.addTask(task);
		return new ResponseEntity<TaskDTO>(taskAdded, HttpStatus.OK);
	}
	
	//morgan added HATEOAS to the system from here look at how it works
	
}
