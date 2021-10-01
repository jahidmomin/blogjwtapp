package com.springboot.blog.app.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.app.model.Post;
import com.springboot.blog.app.service.IPostService;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

	@Autowired
	private IPostService service;

	@GetMapping("/posts")
	public List<Post> getPosts() {
		return (List<Post>) service.findAll();
	}

	@PostMapping("/post")
	public Post createPost(@RequestBody Post post) {
		return service.save(post);
	}

	@PutMapping("/post/{id}")
	public Post updatePost(@PathVariable("id") int id, @RequestBody Post post) {
		Post post2 = service.findById(id).get();
		post2.setTitle(post.getTitle());
		post2.setDescription(post.getDescription());
		post2.setModified_date(new Date(new java.util.Date().getTime()));
		return service.save(post2);
	}

	@DeleteMapping("/{id}")
	public Post deletePost(@PathVariable("id") int id) {
		Post post=service.findById(id).get();
		service.deleteById(id);
		return post;
	}
}
