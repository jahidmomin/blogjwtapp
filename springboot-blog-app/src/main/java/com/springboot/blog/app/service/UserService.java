package com.springboot.blog.app.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.blog.app.dao.RoleDao;
import com.springboot.blog.app.dao.UserDao;
import com.springboot.blog.app.model.Role;
import com.springboot.blog.app.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleDao roledao;
	
	public User registerNewUser(User user) {
		Role role=roledao.findByRoleName("User");
		Set<Role> roles=new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		user.setUserPassword(getEncodedPassword(user.getUserPassword()));
		
		return dao.save(user);
	}
	
	public void initRolesAndUser() {
		Role arole=new Role();
		arole.setRoleId(1);
		arole.setRoleDescription("Admin Role For App");
		arole.setRoleName("Admin");
		roledao.save(arole);
		
		
		Role urole=new Role();
		urole.setRoleId(2);
		urole.setRoleDescription("Default Role For Newly Created Users");
		urole.setRoleName("User");
		roledao.save(urole);
		
		User admin=new User();
		admin.setId(101);
		admin.setUserFirstName("admin");
		admin.setUserLastName("admin");
		admin.setUserName("admin123");
		admin.setUserPassword(getEncodedPassword("admin@pass"));
		Set<Role> roles=new HashSet<>();
		roles.add(arole);
		admin.setRoles(roles);
		dao.save(admin);
		
//		User normalUser=new User();
//		normalUser.setId(102);
//		normalUser.setUserFirstName("jahid");
//		normalUser.setUserLastName("momin");
//		normalUser.setUserName("jahid313");
//		normalUser.setUserPassword(getEncodedPassword("jahid@pass"));
//		Set<Role> userRoles=new HashSet<>();
//		userRoles.add(urole);
//		normalUser.setRoles(userRoles);
//		dao.save(normalUser);
		
		
	}
	
	public String getEncodedPassword(String password) {
		return encoder.encode(password);
	}
}
