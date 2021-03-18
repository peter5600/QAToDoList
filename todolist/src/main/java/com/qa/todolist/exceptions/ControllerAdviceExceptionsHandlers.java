package com.qa.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerAdviceExceptionsHandlers {

	//example exception
	/*
	 * @ExceptionHandler(value = DuckNotFoundException.class)
	public ResponseEntity<String> duckNotFoundExceptionHandler(DuckNotFoundException dnfe) {
		
		// Spring automatically passes the exception to our method parameter
		
		return new ResponseEntity<String>(dnfe.getMessage(), HttpStatus.NOT_FOUND);
	}
	 */
	@ExceptionHandler(value = ListsTasksNotFound.class)
	public ResponseEntity<String> ListsTasksNotFound(ListsTasksNotFound ltnf){
		return new ResponseEntity<String>(ltnf.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ListNotFoundException.class)
	public ResponseEntity<String> ListNotFoundException(ListNotFoundException lntf){
		return new ResponseEntity<String>(lntf.getMessage(), HttpStatus.NOT_FOUND);
	}
}
