package com.qa.todolist.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;


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
}
