package com.ssamz.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
	@GetMapping({"/jblog/view"})	// RESTController의 GetMapping과 겹쳐서 jsp파일이 나오지 않아 수정. 원래는 @GetMapping({"", "/"})
	public String getPostList() 
	{
		return "index";	
	}
	
	@GetMapping("/post/insertPost")
	public String insertPost() 
	{
		return "post/insertPost";
	}
}
