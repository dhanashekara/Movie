package com.mymovie.exception;

public class TicketNotAvailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TicketNotAvailableException(String message) {

		super(message);
	}

}
