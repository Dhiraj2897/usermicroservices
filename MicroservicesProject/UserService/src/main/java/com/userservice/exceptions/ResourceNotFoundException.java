package com.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private String name;

	private String mail;

	private String about;

	public ResourceNotFoundException(String name, String mail, String about) {
		super(String.format("%s not found with %s : '%s'", name, mail, about));
		this.name = name;
		this.mail = mail;
		this.about = about;
	}

	

}
