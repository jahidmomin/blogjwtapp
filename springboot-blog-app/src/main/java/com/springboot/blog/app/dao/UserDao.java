package com.springboot.blog.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.blog.app.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer>{
	User findByUserName(String username);
}
