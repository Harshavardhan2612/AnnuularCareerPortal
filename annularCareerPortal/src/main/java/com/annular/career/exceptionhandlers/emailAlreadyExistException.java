package com.annular.career.exceptionhandlers;

public class emailAlreadyExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public emailAlreadyExistException(String message) {
		super(message);
	}
}
