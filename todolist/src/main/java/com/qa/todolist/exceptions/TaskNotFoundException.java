package com.qa.todolist.exceptions;

import javax.persistence.EntityNotFoundException;

public class TaskNotFoundException extends EntityNotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3294257868828998727L;

	public TaskNotFoundException(){
		super();
	}
	
	public TaskNotFoundException(String message) {
		super(message);
	}
}
