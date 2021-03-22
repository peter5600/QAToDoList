package com.qa.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerAdviceExceptionsHandlers {

	@ExceptionHandler(value = ListsTasksNotFound.class)
	public ResponseEntity<String> ListsTasksNotFound(ListsTasksNotFound ltnf){
		return new ResponseEntity<String>(ltnf.getMessage(), HttpStatus.NO_CONTENT);//this is no content i just wanted to know because 
		//if no tasks are found its unusal behaviour but its acceptable since it could be a new list
		//its not ok because 
	}
	
	@ExceptionHandler(value = ListNotFoundException.class)
	public ResponseEntity<String> ListNotFoundException(ListNotFoundException lnf){
		return new ResponseEntity<String>(lnf.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = TaskNotFoundException.class)
	public ResponseEntity<String> TaskNotFoundException(TaskNotFoundException tnf){
		return new ResponseEntity<String>(tnf.getMessage(), HttpStatus.NOT_FOUND);
	}
}
