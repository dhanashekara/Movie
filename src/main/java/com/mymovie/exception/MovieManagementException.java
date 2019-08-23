package com.mymovie.exception;

public class MovieManagementException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MovieManagementException(String message) {
		
		super(message);
	}
}
