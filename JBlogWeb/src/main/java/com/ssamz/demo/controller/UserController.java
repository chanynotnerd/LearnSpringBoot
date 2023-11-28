package com.ssamz.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssamz.demo.domain.User;
import com.ssamz.demo.dto.ResponseDTO;
import com.ssamz.demo.dto.UserDTO;
import com.ssamz.demo.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	private UserService userService; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/auth/insertUser")
	public String insertUser()
	{
		return "user/insertUser";
	}
	
	@PostMapping("/auth/insertUser")
	public @ResponseBody ResponseDTO<?> insertUser(
			@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult)
	{
//		// UserDTO 객체에 대한 유효성 검사
//		if(bindingResult.hasErrors())
//		{
//			// 에러가 하나라도 있다면 에러 메세지를 Map에 등록
//			Map<String, String> errorMap = new HashMap<>();
//			for (FieldError error : bindingResult.getFieldErrors())
//			{
//				errorMap.put(error.getField(), error.getDefaultMessage());
//			}
//			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
//		}
		
		// UserDTO -> User 객체로 변환
		User user = modelMapper.map(userDTO, User.class);
		User findUser = userService.getUser(user.getUsername());
		
		if(findUser.getUsername() == null)
		{
			userService.insertUser(user);
			return new ResponseDTO<>(HttpStatus.OK.value(),
					user.getUsername() + "님 가입 성공");
		}
		else
		{
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(),
					user.getUsername() + "님은 이미 회원이십니다.");
		}
		
		// userService.insertUser(user);
		// return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + "님 회원가입 성공!");
	}
}


/*
 * @Autowired private UserRepository userRepository;
 * 
 * @GetMapping("/user/page") public @ResponseBody Page<User> getUserListPaging(
 * 
 * @PageableDefault(page = 0, size = 2, direction = Sort.Direction.DESC, sort =
 * { "id", "username" }) Pageable pageable) { return
 * userRepository.findAll(pageable); }
 * 
 * @GetMapping("/user/list") public @ResponseBody List<User> getUserList() {
 * return userRepository.findAll(); }
 * 
 * @DeleteMapping("/user/{id}") public @ResponseBody String
 * deleteUser(@PathVariable int id) { userRepository.deleteById(id); return
 * "회원 삭제 성공"; }
 * 
 * // @Transactional
 * 
 * @PutMapping("/user") public @ResponseBody String updateUser(@RequestBody User
 * user) { User findUser = userRepository.findById(user.getId()).orElseThrow(()
 * -> { return new JBlogException(user.getId() + "번 회원이 없습니다."); });
 * findUser.setUsername(user.getUsername());
 * findUser.setUsername(user.getPassword()); findUser.setEmail(user.getEmail());
 * 
 * userRepository.save(findUser); // save() 메소드 대신 @Transactional 어노테이션을 걸고
 * userRepository.save(findUser); 이 줄 지워도 // 된다. return "회원 수정 성공"; }
 * 
 * @GetMapping("/user/get/{id}") public @ResponseBody User getUser(@PathVariable
 * int id) { // 특정 id(회원번호)에 해당하는 User 객체 반환, 검색된 회원이 없을 경우 예외 반환 User findUser
 * = userRepository.findById(id).orElseThrow(new Supplier<JBlogException>() {
 * 
 * @Override public JBlogException get() { return new JBlogException(id +
 * "번 회원이 없습니다."); } });
 * 
 * User findUser ~ }); 까지의 내용은 추상메소드가 하나이므로 람다식으로도 표현 가능 User findUser =
 * userRepository.findById(id).orElseThrow(()->{ return new JBlogException(id +
 * "번 회원이 없습니다."); });
 * 
 * 
 * return findUser; }
 * 
 * @PostMapping("/user") public @ResponseBody String insertUser(@RequestBody
 * User user) { user.setRole(RoleType.USER); userRepository.save(user); return
 * user.getUsername() + " 회원가입 성공"; }
 */