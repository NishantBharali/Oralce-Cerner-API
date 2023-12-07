package com.backend.dev;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.dev.model.Idea;
import com.backend.dev.model.User;
import com.backend.dev.repositories.IdeaRepository;
import com.backend.dev.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class EntityCheckTest {
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private IdeaRepository ideaRepository;

	@Test
	void testController() {
		User user = new User("test@gmail.com", "test123");
		when(ideaRepository.findAll()).thenReturn(Stream.of(new Idea(user, "test", "Test description", 2), new Idea(user, "test1", "Test1 description", 3)).collect(Collectors.toList()));
		assertEquals(2, ideaRepository.findAll().size());
	}
	
}
