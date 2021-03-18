package com.qa.todolist.exceptions;

import javax.persistence.EntityNotFoundException;

public class ListNotFoundException extends EntityNotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5469905067521813272L;
	
	public ListNotFoundException() {
		super();
	}
	
	public ListNotFoundException(String message) {
		super(message);
	}
}
