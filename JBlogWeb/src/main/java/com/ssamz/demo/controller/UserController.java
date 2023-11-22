package com.ssamz.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@GetMapping("/auth/insertUser")
	public String insertUser()
	{
		return "user/insertUser";
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