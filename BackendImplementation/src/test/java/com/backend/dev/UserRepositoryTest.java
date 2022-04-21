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
class UserRepositoryTest {
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	void testCreateUser() {
		User user = new User();
		user.setEmail("nishant.b@gmail.com");
		user.setPassword("Nish2021");
		
		User savedUser = userrepo.save(user);
		User existUser = entityManager.find(User.class, savedUser.getEmail());
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
		
	}
	


}

