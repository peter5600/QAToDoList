package com.qa.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@GetMapping("/tasks/{id}")
	public ResponseEntity<TaskDTO> getTaskByListID(@PathVariable("id") Integer id){
		return null;
	}
	
	//modify tasks
	@PatchMapping("/{id}")
	public ResponseEntity<TaskDTO> modifyTaskByID(@PathVariable("id") Integer id){
		return null;
	}
	
	//delete tasks
	

	//add tasks
	
	
	//morgan added HATEOAS to the system from here look at how it works
	
}
