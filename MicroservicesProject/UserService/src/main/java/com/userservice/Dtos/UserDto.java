package com.userservice.Dtos;

import lombok.Data;

@Data
public class UserDto {
	private String userId;

	private String name;

	private String mail;

	private String about;
}
