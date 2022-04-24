package com.backend.dev;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.backend.dev.model.User;
import com.backend.dev.repositories.UserRepository;
import com.backend.dev.security.CustomUserDetailsService;
import com.backend.dev.services.DatabaseService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CUDTest {
	
	@InjectMocks
	CustomUserDetailsService service;
	
	@MockBean
	DatabaseService databaseService;
	
	@MockBean
	UserRepository userRepository;
	
	@Autowired
	MockMvc mvc;

	@Test
	void test() {
//		CustomUserDetailsService newObj = new CustomUserDetailsService();
		Optional<User> user = Optional.ofNullable(new User("test", "test"));
		 service.setDatabaseService(databaseService);
   		 when(databaseService.getUserRepository()).thenReturn(userRepository);
		Mockito.when(userRepository.findByEmail("test")).thenReturn(user);
		assertEquals("test", service.loadUserByUsername("test").getUsername());
	}
	

}
