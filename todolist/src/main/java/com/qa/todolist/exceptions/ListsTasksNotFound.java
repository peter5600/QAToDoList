package com.qa.todolist.exceptions;

import javax.persistence.EntityNotFoundException;

public class ListsTasksNotFound extends EntityNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6630833675634463034L;
	
	public  ListsTasksNotFound() {
		super();
	}
	
	public ListsTasksNotFound(String message) {
		super(message);
	}

}
