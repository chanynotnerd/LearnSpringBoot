package com.ssamz.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssamz.demo.domain.Post;
import com.ssamz.demo.domain.Reply;
import com.ssamz.demo.domain.User;
import com.ssamz.demo.persistence.PostRepository;
import com.ssamz.demo.persistence.ReplyRepository;

@Service
public class ReplyService {
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Transactional
	public void insertReply(int postId, Reply reply, User user)
	{
		Post post = postRepository.findById(postId).get();
		reply.setUser(user);
		reply.setPost(post);
		replyRepository.save(reply);
	}
}
