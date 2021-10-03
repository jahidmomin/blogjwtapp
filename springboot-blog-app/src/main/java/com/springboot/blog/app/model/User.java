package com.springboot.blog.app.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity
public class User {
	private int id;
	private String userFirstName;
	private String userLastName;
	private String userName;
	private String userPassword;
	//	for diff roles 
	//we need association MtoM
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE",joinColumns = {
			@JoinColumn(name="USER_ID")
	},inverseJoinColumns = {
			@JoinColumn(name="ROLE_ID")
	})
	private Set<Role> roles;
	
}
