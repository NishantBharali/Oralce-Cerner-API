package com.backend.dev;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.backend.dev.controller.UserController;
import com.backend.dev.model.User;
import com.backend.dev.repositories.UserRepository;
import com.backend.dev.services.DatabaseService;

@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class UserController2Test {

	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private DatabaseService databaseService;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Before
	void setup() {
		userController = new UserController();
		userController.setDatabaseService(databaseService);
	}
	
	 @Test
	   void databaseServiceGetterTest() {
		   when(databaseService.getUserRepository()).thenReturn(userRepository);
		   assertNotNull(userController.getDatabaseService());
	   }
	
	
	@Test
	void addUserAlreadyExistsErrorResponseTest() {
		Optional<User> user = Optional.ofNullable(new User("test3@gmail.com", "test3"));
		User user1 = new User();
		user1.setEmail("test3@gmail.com");
		user1.setPassword("test3");
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		when(userRepository.findByEmail("test3@gmail.com")).thenReturn(user);
		
		ResponseEntity<?> responseEntity = userController.addUser(user1);
		
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
		assertThat(responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST));
	}
	
}
