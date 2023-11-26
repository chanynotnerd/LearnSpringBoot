package com.ssamz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssamz.demo.domain.Post;
import com.ssamz.demo.domain.User;
import com.ssamz.demo.dto.ResponseDTO;
import com.ssamz.demo.service.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	
	@PostMapping("/post")
	public @ResponseBody ResponseDTO<?> insertPost(@RequestBody Post post,
			HttpSession session)
	{
		// Post객체를 영속화하기 전 연관된 User 엔티티 설정
		User principal = (User) session.getAttribute("principal");
		post.setUser(principal);
		post.setCnt(0);
		
		postService.insertPost(post);
		
		return new ResponseDTO<>(HttpStatus.OK.value(),
				"새로운 포스트를 등록했습니다.");
	}
	@GetMapping({"/jblog/view"})	// getmapping을 직접 해버렸다. 500 에러가 또 떠서.
	public String getPostList(Model model, @PageableDefault(size = 3, sort = "id",
	direction = Direction.DESC) Pageable pageable)
	{
		model.addAttribute("postList", postService.getPostList(pageable));
		return "index";
	}
	
	/*@GetMapping({"/jblog/view"})	// RESTController의 GetMapping과 겹쳐서 jsp파일이 나오지 않아 수정. 원래는 @GetMapping({"", "/"})
	public String getPostList() 
	{
		return "index";	
	}*/
	
	@GetMapping("/post/insertPost")
	public String insertPost() 
	{
		return "post/insertPost";
	}
}
