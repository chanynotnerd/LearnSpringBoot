package com.ssamz.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	@GetMapping("/html")
	public String html() 
	{
		System.out.println("HTML 파일 요청");
		return "redirect:image/hello.html";
	}
	
	@GetMapping("/image")
	public String image()
	{
		System.out.println("이미지 파일이 요청");
		return "redirect:image/chanychicken.png";
	}
	
	@GetMapping("/jsp")
	public String jsp(Model model)
	{
		System.out.println("jsp 파일이 요청");
		model.addAttribute("username", "찬영");
		return "hello";
	}
}
