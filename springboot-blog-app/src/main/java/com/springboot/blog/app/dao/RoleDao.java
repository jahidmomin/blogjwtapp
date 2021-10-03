package com.springboot.blog.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.blog.app.model.Role;



@Repository
public interface RoleDao extends CrudRepository<Role, Integer> {

}
