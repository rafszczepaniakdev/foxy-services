package com.foxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foxy.domain.sql.User;
import com.foxy.repository.sql.UserRepository;

@RestController
public class MainController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/")
	public String hello(){
		return "Hello";
	}
	
	@RequestMapping("/msg")
	public String getMessage(){
		User user = new User();
		user.setUsername("test1");
		user.setPassword("asd");
		userRepository.save(user);
		return "Lorem Ipsum Dolor Sit Amet...";
	}
	
	@RequestMapping(value="/get", method = RequestMethod.POST)
	public String getUser(){
		User user = userRepository.findByUsername("test");
		return user.getUsername();
	}
	
}