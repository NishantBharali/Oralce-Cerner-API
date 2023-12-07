package com.backend.dev;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.dev.repositories.IdeaRepository;
import com.backend.dev.repositories.UserRepository;
import com.backend.dev.services.DatabaseService;

@RunWith(SpringRunner.class)
@SpringBootTest
class DatabaseServiceTest {

	@MockBean
	UserRepository userRepository;
	
	@MockBean
	IdeaRepository ideaRepository;
	
	@InjectMocks
	DatabaseService databaseService;
	
	@Test
	void test() {
		assertNotNull(userRepository.findAll());
		assertEquals(null, databaseService.getUserRepository()); 
		assertEquals(null, databaseService.getIdeaRepository());
		assertNotNull(ideaRepository.findAll());
	}
	
	@Test
	void databaseTest() {
		DatabaseService obj = new DatabaseService();
		assertNull(obj.getUserRepository());
		assertNull(obj.getIdeaRepository());
	}

}
