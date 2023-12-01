package com.ssamz.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssamz.demo.domain.Post;
import com.ssamz.demo.domain.User;
import com.ssamz.demo.dto.PostDTO;
import com.ssamz.demo.dto.ResponseDTO;
import com.ssamz.demo.security.UserDetailsImpl;
import com.ssamz.demo.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@DeleteMapping("/post/{id}")
	public @ResponseBody ResponseDTO<?> deletePost(@PathVariable int id)
	{
		postService.deletePost(id);
		return new ResponseDTO<>(HttpStatus.OK.value(),
				id + "번 포스트를 삭제했습니다.");
	}
	
	@PutMapping("/post")
		public @ResponseBody ResponseDTO<?> updatePost(@RequestBody Post post)
		{
			postService.updatePost(post);
			return new ResponseDTO<>(HttpStatus.OK.value(),
					post.getId() + "번 포스트를 수정했습니다.");
		}
	
	@GetMapping("/post/updatePost/{id}")
	public String updatePost(@PathVariable int id, Model model,
			 @AuthenticationPrincipal UserDetailsImpl principal)
	
	{
		model.addAttribute("post", postService.getPost(id));
		if (principal != null) {
			model.addAttribute("currentUser", principal.getUser());
		}
		return "post/updatePost";
	}
	
	@GetMapping("/post/{id}")
	public String getPost(@PathVariable int id, Model model,  @AuthenticationPrincipal UserDetailsImpl principal)
	{
		model.addAttribute("post", postService.getPost(id));
		if (principal != null) {
			model.addAttribute("currentUser", principal.getUser());
		}
		return "post/getPost";
	}
	
	@PostMapping("/post")
	public @ResponseBody ResponseDTO<?> insertPost(
			@Valid @RequestBody PostDTO postDTO, BindingResult bindingResult,
			@AuthenticationPrincipal UserDetailsImpl principal)
	{
//		// PostDTO 객체에 대한 유효성 검사
//		if(bindingResult.hasErrors())
//		{
//			// 에러가 하나라도 있다면 에러 메세지를 Map에 등록
//			Map<String, String> errorMap = new HashMap<>();
//			for(FieldError error : bindingResult.getFieldErrors())
//			{
//				errorMap.put(error.getField(), error.getDefaultMessage());
//			}
//			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
//			}
		// PostDTO -> Post 객체로 변환
		Post post = modelMapper.map(postDTO, Post.class);
		
		// Post객체를 영속화하기 전 연관된 User 엔티티 설정
		post.setUser(principal.getUser());
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
