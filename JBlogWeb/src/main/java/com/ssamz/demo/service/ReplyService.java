package com.ssamz.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssamz.demo.domain.Post;
import com.ssamz.demo.domain.Reply;
import com.ssamz.demo.domain.User;
import com.ssamz.demo.persistence.PostRepository;
import com.ssamz.demo.persistence.ReplyRepository;
import com.ssamz.demo.security.UserDetailsImpl;

@Service
public class ReplyService {
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Transactional
	public void deleteReply(int replyId, UserDetailsImpl principal)
	{
		Reply reply = replyRepository.findById(replyId)
	            .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + replyId));
	        
	        User currentUser = principal.getUser();
	        if (reply.getUser().getId() != currentUser.getId()) {
	            throw new IllegalArgumentException("댓글을 삭제할 권한이 없습니다.");
	        }
		
		replyRepository.deleteById(replyId);
	}
	
	@Transactional
	public void insertReply(int postId, Reply reply, User user)
	{
		Post post = postRepository.findById(postId).get();
		reply.setUser(user);
		reply.setPost(post);
		replyRepository.save(reply);
	}
}
