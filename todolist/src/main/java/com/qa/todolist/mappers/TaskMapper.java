package com.qa.todolist.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qa.todolist.data.model.Task;
import com.qa.todolist.dto.TaskDTO;


@Component
//this will use the modelMapper class to map the data from a DTO to an object and vice versa
public class TaskMapper {

	private ModelMapper modelMapper;
	
	@Autowired//spring will inject the value for me
	public TaskMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public TaskDTO mapToDTO(Task task) {
		return this.modelMapper.map(task, TaskDTO.class);
	}
	
	public Task mapToDuck(TaskDTO taskDTO) {
		return this.modelMapper.map(taskDTO, Task.class);
	}
}
