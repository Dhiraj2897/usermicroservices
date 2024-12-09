package com.userservice.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rating.entities.Rating;
import com.userservice.Dtos.UserDto;
import com.userservice.entities.User;
import com.userservice.exceptions.ResourceNotFoundException;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;

@Service
public class userseviceimpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(userseviceimpl.class);

	public userseviceimpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	// convert Entity to dto
	private UserDto maptoDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setName(user.getName());
		userDto.setMail(user.getMail());
		userDto.setAbout(user.getAbout());
		return userDto;

	}

	// covert dto to Entity
	private User mapToEnity(UserDto userDto) {
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setName(userDto.getName());
		user.setMail(userDto.getMail());
		user.setAbout(userDto.getAbout());
		return user;
	}

	public UserDto saveUser(UserDto userDto) {
		// generate unique userId
		String randomUserId = UUID.randomUUID().toString();

		// covert dto to entity
		User user = mapToEnity(userDto);
		User newuser = userRepository.save(user);

		user.setUserId(randomUserId);
		// convert entity to dto

		UserDto userSaved = maptoDto(newuser);
		return userSaved;
	}

	public UserDto getUser(String userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		// fetch rating of user from rating service
		// http://localhost:8083/ratings/users/6
		@SuppressWarnings("unchecked")
		ArrayList<Rating> ratingsOfUser = restTemplate
				.getForObject("http://localhost:8083/ratings/users/6" + user.getUserId(), ArrayList.class);
		logger.info("{}", ratingsOfUser);
		user.setRatings(ratingsOfUser);
		return maptoDto(user);
	}

	public List<UserDto> getAllUser() {
		List<User> users = userRepository.findAll();

		return users.stream().map(user -> maptoDto(user)).collect(Collectors.toList());
	}

	public UserDto updateUser(UserDto userDto, String userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		user.setName(userDto.getName());
		user.setMail(userDto.getMail());
		user.setAbout(userDto.getAbout());

		User updateuser = userRepository.save(user);

		return maptoDto(updateuser);

	}

	public void deleteUser(String userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "userId", userId));
		userRepository.delete(user);
	}

}
