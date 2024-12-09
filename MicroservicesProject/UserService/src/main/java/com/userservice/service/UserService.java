package com.userservice.service;

import java.util.List;

import com.userservice.Dtos.UserDto;

public interface UserService {

	// save user
	UserDto saveUser(UserDto userDto);

	// getuser
	UserDto getUser(String userId);

	// getAllUser
	List<UserDto> getAllUser();

	// update user
	UserDto updateUser(UserDto userDto, String userId);

	// delete user
	void deleteUser(String userId);
}
