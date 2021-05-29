package com.springmongo.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springmongo.project.model.User;
import com.springmongo.project.repositories.UserRepository;

@RestController
@RequestMapping(value="/")
public class UserController {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	private List<User> getAllUsers(){
		log.info("Creating all users");
		return userRepository.findAll();
	}
	
	
	@RequestMapping(value = "{userId}",method = RequestMethod.GET)
	public User getUser(@PathVariable String userId) {
		log.info("Getting user ID with ID: "+userId);
		return null; //userRepository.findOne(userId);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public User addNewUsers(@RequestBody User user) {
		log.info("Saving user.");
		return userRepository.save(user);
	}

	
	@RequestMapping(value = "/settings/{userId}",method = RequestMethod.GET)
	public Object getAllUserSettings(@PathVariable String userId,@PathVariable String key) {
		User user = null; //userRepository.findOne(userId);
		if(user!= null) {
			return user.getUserSettings().get(key);
		}else {
			return "User not found";
		}
	}

	
	@RequestMapping(value = "/settings/{userId}/{key}/{value}",method = RequestMethod.POST)
	public String addUserSettings(@PathVariable String userId,@PathVariable String key,@PathVariable String value) {
		User user = null;//userRepository.findOne(userId);
		if (user != null) {
			user.getUserSettings().put(key, value);
			userRepository.save(user);
			return "Key added";
		} else {
			return "User not found.";
		}

		
		
	}
	
}
