package com.backend.dev;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.dev.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
class UsersModelTest {

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void modelUserTest() {
		User userObj = new User("nishant@gmail.com", "nish@123");
		User userObj1 = new User("nishant@gmail.com", "nish@123");
		userObj.setIdeas(null);
		userObj.getIdeas();
		
		assertEquals(null, userObj.getIdeas());
		assertEquals(userObj.hashCode(), userObj1.hashCode());
		assertEquals(userObj1, userObj);
		assertEquals(userObj, userObj1);
		assertEquals(true, userObj.equals(userObj));
		assertEquals(false, userObj.equals(null));
		assertEquals(false, userObj.equals(userObj.getClass()));
	}
	
	@Test
	void testUserToString() {
		User obj1 = new User("foo@gmail.com", "foo@123");
		User obj2 = new User("foo@gmail.com", "foo@123");
		String email = obj2.getEmail();
		String password = obj2.getPassword();
		assertEquals("User [email=" + email + ", password=" + password + "]", obj1.toString());
	}
	
	

}
