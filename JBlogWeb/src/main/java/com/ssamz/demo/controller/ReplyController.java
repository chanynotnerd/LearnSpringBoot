package com.ssamz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssamz.demo.domain.Reply;
import com.ssamz.demo.domain.User;
import com.ssamz.demo.dto.ResponseDTO;
import com.ssamz.demo.service.ReplyService;

import javax.servlet.http.HttpSession;

@Controller
public class ReplyController {
	@Autowired
	private ReplyService replyService;
	
	@DeleteMapping("/reply/{replyId}")
	public @ResponseBody ResponseDTO<?> deleteReply(@PathVariable int replyId) {
		replyService.deleteReply(replyId);
		return new ResponseDTO<>(HttpStatus.OK.value(),
				replyId+"번 댓글이 삭제되었습니다.");
	}
	
	@PostMapping("/reply/{postId}")
	public @ResponseBody ResponseDTO<?> insertReply(@PathVariable int postId,
			@RequestBody Reply reply, HttpSession session) {
		User principal = (User) session.getAttribute("principal");
		replyService.insertReply(postId, reply, principal);
		return new ResponseDTO<>(HttpStatus.OK.value(),
				postId + "번 포스트에 대한 댓글이 등록되었습니다.");
	}
}
