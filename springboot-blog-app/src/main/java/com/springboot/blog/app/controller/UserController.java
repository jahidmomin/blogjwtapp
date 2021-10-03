package com.springboot.blog.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.app.model.User;
import com.springboot.blog.app.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping({"/registerNewUser"})
	public User registerNewUser(@RequestBody User user) {
		return service.registerNewUser(user);
	}
}
