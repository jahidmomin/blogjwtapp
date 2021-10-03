package com.springboot.blog.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.app.model.Role;
import com.springboot.blog.app.service.RoleService;



@RestController
public class RoleController {
	@Autowired
	private RoleService service;
	
	@PostMapping("/createNewRole")
	public Role createNewRole(@RequestBody Role role) {
		return service.createNewRole(role);
	}
	
	@GetMapping("/hey")
	public String hey() {
		return "role";
	}
}
