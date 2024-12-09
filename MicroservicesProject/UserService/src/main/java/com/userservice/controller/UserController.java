package com.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.Dtos.UserDto;
import com.userservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userservice;

	public UserController(UserService userservice) {
		super();
		this.userservice = userservice;
	}

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		return new ResponseEntity<>(userservice.saveUser(userDto), HttpStatus.CREATED);

	}

	@GetMapping
	public List<UserDto> getAllUsers() {
		return userservice.getAllUser();
	}

	// getuser

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
		return ResponseEntity.ok(userservice.getUser(userId));

	}

	@PutMapping("/{userId}/update")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable String userId) {
		UserDto postResponse = userservice.updateUser(userDto, userId);
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}

	// delete post rest api
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable String userId) {

		userservice.deleteUser(userId);
		return new ResponseEntity<>("User has been deleted successfully.", HttpStatus.OK);
	}
}
