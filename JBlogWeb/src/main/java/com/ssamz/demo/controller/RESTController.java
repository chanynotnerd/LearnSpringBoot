package com.ssamz.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ssamz.demo.domain.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class RESTController {

	// GET: SELECT
	@GetMapping("/jblog")
	public User httpGet() {
		User findUser = User.builder()
				.id(1)
				.username("gurum")
				.password("222")
				.email("gurum@gmail.com")
				.build();
		return findUser;
	}

	// POST: INSERT
	@PostMapping("/jblog")
	public String httpPost(@RequestBody User user) {
		return "POST 요청 처리 입력값 : " + user.toString();
	}

	// PUT: UPDATE
	@PutMapping("/jblog")
	public String httpPut(@RequestParam User user) {
		return "Put 요청 처리 입력값 : " + user.toString();
	}

	// DELETE: DELETE
	@DeleteMapping("/jblog")
	public String httpDelete(@RequestParam int id) {
		return "Delete 요청 처리 입력값 : " + id;
	}
}
