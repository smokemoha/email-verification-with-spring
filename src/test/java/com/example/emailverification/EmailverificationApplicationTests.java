package com.example.emailverification;

import com.example.emailverification.entity.User;
import com.example.emailverification.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EmailverificationApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private UserService userService;
	
	@Test
	@Transactional
	void testUserRegistration() {
		// Create a test user
		User testUser = new User();
		testUser.setFirstName("Test");
		testUser.setLastName("User");
		testUser.setEmail("test" + System.currentTimeMillis() + "@example.com");
		testUser.setUsername("testuser" + System.currentTimeMillis());
		testUser.setPassword("password123");
		
		// Register the user
		User registeredUser = userService.registerUser(testUser);
		
		// Verify the user was registered correctly
		assertNotNull(registeredUser);
		assertNotNull(registeredUser.getId());
		assertEquals(testUser.getFirstName(), registeredUser.getFirstName());
		assertEquals(testUser.getLastName(), registeredUser.getLastName());
		assertEquals(testUser.getEmail(), registeredUser.getEmail());
		assertEquals(testUser.getUsername(), registeredUser.getUsername());
		assertEquals(false, registeredUser.isEnabled()); // User should not be enabled until email verification
	}
}