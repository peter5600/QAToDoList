package com.qa.todolist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.todolist.data.repository.TaskRepository;
import com.qa.todolist.mappers.TaskMapper;

@Service
public class TaskService {

	//this lets me interact with the db
	private TaskRepository taskRepo;
	
	//lets me map task to taskdto and vice versa 
	private TaskMapper taskMapper;
	
	@Autowired//spring inject values in for me
	public TaskService(TaskRepository taskRepo, TaskMapper taskMapper) {
		this.taskRepo = taskRepo;
		this.taskMapper = taskMapper;
	}
	
	//from here its just the different functions that ill need to add 
	
}
