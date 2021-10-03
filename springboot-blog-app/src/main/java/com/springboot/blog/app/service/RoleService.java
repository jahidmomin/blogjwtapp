package com.springboot.blog.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blog.app.dao.RoleDao;
import com.springboot.blog.app.model.Role;

@Service
public class RoleService {
	
	@Autowired
	private RoleDao dao;
	
	public Role createNewRole(Role role) {
		return dao.save(role);
	}
}
