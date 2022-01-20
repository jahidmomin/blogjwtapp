package com.springboot.blog.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class JwtRequest {
	private String userName;
	private String userPassword;
}
