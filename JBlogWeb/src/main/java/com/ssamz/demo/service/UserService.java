package com.ssamz.demo.service;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ssamz.demo.domain.RoleType;
import com.ssamz.demo.domain.User;
import com.ssamz.demo.persistence.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public User getUser(String username) 
	{
		// 검색결과가 없을 때 빈 User 객체로 반환
		User findUser = userRepository.findByUsername(username).orElseGet(
				new Supplier<User>() {
					@Override
					public User get() {
						return new User();
					}
				});
		return findUser;
	}
	
	@Transactional
	public void insertUser(User user)
	{
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
}
