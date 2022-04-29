package com.backend.dev;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import com.backend.dev.repositories.*;
import com.backend.dev.model.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class IdeaRepositoryTest {
	
	@Autowired
	private IdeaRepository idearepo;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	
	@Test
	void testCreateIdea() {
		Idea idea = new Idea();
		idea.setIdeaTitle("Student Management strategy");
		idea.setIdeaDescription("A better way to manage a student's education.");
		idea.setIdeaStorypoints(4);
		
		Idea idea1 = new Idea();
		idea1.setIdeaTitle("strategy");
		idea1.setIdeaDescription("something.");
		idea1.setIdeaStorypoints(2);
		
		User user = new User();
		user.setEmail("nishant.b@gmail.com");
		user.setPassword("Nish2021");
		
		User savedUser = userrepo.save(user);
		User existUser = entityManager.find(User.class, savedUser.getEmail());
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
		
		idea.setUser(savedUser);
		idea1.setUser(savedUser);
		
		Idea savedIdea = idearepo.save(idea);
		Idea existIdea = entityManager.find(Idea.class, savedIdea.getId());
		assertThat(existIdea.getIdeaTitle()).isEqualTo(idea.getIdeaTitle());
		
		Idea savedIdea1 = idearepo.save(idea1);
		Idea existIdea1 = entityManager.find(Idea.class, savedIdea1.getId());
		assertThat(existIdea1.getIdeaTitle()).isEqualTo(idea1.getIdeaTitle());
		
	}
	
}
