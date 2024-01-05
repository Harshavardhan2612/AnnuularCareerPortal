package com.annular.career.exceptionhandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(emailAlreadyExistException.class)
	public ResponseEntity<String> handleEmailAlreadyExistsException(emailAlreadyExistException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
