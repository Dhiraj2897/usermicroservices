package com.hotelservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String s) {
		super(s);
	}

public ResourceNotFoundException() {
	super("resource are not found..!");
}
}
