package com.springboot.blog.app.service;

import org.springframework.data.repository.CrudRepository;

import com.springboot.blog.app.model.Post;

public interface IPostService extends CrudRepository<Post, Integer> {

}
