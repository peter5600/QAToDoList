package com.qa.todolist.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.todolist.data.model.Task;
import com.qa.todolist.data.repository.TaskRepository;
import com.qa.todolist.dto.TaskDTO;
import com.qa.todolist.exceptions.ListsTasksNotFound;
import com.qa.todolist.exceptions.TaskNotFoundException;
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
	
	public List<TaskDTO> returnAllTasksFromListByID(Integer id){
		List<Task> tasks = taskRepo.findAllTasksFromListID(id);
		
		if(tasks.size() > 0) {
			return taskMapper.mapToTaskDTOFromList(tasks);
		}else {
			//might cause problems if i create a list and then exit since no tasks will
			//ever be made for that list
			throw new ListsTasksNotFound("No tasks were found for that list");
		}
	}
	//from here its just the different functions that ill need to add 

	public TaskDTO modifyTask(Integer id, @Valid Task task) {
		Optional<Task> optionalTask = taskRepo.findById(id);
		Task foundTask;
		if(optionalTask.isPresent()) {
			foundTask = optionalTask.get();
		}else {
			throw new TaskNotFoundException("Task by id: " + id + " wasn't found");
		}
		
		foundTask.setTask(task.getTask());
		foundTask.setTaskCompleted(task.getTaskCompleted());
		
		Task updatedTask = taskRepo.save(foundTask);
		
		return taskMapper.mapToDTO(updatedTask);
	}

	public Boolean completeTask(Integer id) {
		Optional<Task> optionalTask = taskRepo.findById(id);
		Task foundTask;
		if(optionalTask.isPresent()) {
			foundTask = optionalTask.get();
		}else {
			throw new TaskNotFoundException("Task by id: " + id + " wasn't found");
		}
		
		foundTask.setTaskCompleted(true);
		
		Task updatedTask = taskRepo.save(foundTask);
		return updatedTask.getTaskCompleted();
	}
	
	public Boolean deleteTask(Integer id) {
		if(!taskRepo.existsById(id)) {
			throw new TaskNotFoundException("Task id: " + id + " wasn't found");
		}else {
			taskRepo.deleteById(id);
		}
		return !taskRepo.existsById(id);
	}
	
	public TaskDTO addTask(Task task) {
		Task savedTask = taskRepo.save(task);
		return taskMapper.mapToDTO(savedTask);
	}

	

	
}
