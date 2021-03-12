package com.qa.todolist.controller;

@RestController
@RequestMapping(path = "/task")
@CrossOrigin // enables cross origin allowing other services like my front end to use this api
//if i get more time then i should look at ways to a list of services that can use the api to secure it
public class TaskController {

	private TaskService taskService;
	
	//morgan added HATEOAS to the system from here look at how it works
	
}
