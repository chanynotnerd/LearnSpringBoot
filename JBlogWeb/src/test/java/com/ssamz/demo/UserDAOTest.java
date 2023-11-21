package com.ssamz.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Configuration;

import com.ssamz.demo.domain.User;
import com.ssamz.demo.persistence.UserDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Configuration
class UserDAOTest {
	@Autowired
	private UserDAO userDAO;

	@Test
	void getUserListTest() {
		User user = new User();
		user.setUsername("test");
		user.setPassword("Test123");
		user.setEmail("test@gmail.com");

		int before = userDAO.getUserList().size();
		userDAO.insertUser(user);
		int after = userDAO.getUserList().size();

		assertEquals(before + 1, after);
	}
}