package com.ssamz.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssamz.demo.domain.Post;
import com.ssamz.demo.persistence.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	@Transactional(readOnly = true)
	public Page<Post> getPostList(Pageable pageable)
	{
		return postRepository.findAll(pageable);
	}
	
	@Transactional
	public void insertPost(Post post)
	{
		post.setCnt(0);
		postRepository.save(post);
	}
}
