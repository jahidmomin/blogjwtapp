package com.springboot.blog.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blog.app.dao.UserDao;
import com.springboot.blog.app.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao dao;
	
	public User registerNewUser(User user) {
		return dao.save(user);
	}
}
