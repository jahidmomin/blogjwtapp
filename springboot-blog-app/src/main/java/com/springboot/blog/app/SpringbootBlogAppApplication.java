package com.springboot.blog.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.EqualsAndHashCode.Include;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(contact = @Contact(email ="mominjahid313@gmail.com",name = "Jahid Momin",url = "jahidmomin.github.io" ), description = "Blog Application In Springboot", version = "1.0", title = "SpringBootBlogger"))

public class SpringbootBlogAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogAppApplication.class, args);
	}

}
