package com.backend.dev;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.dev.model.User;
import com.backend.dev.security.CustomUserDetails;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserDetailsTest {

	@Test
	void testUserDetails() {
		User userObj = new User("nishant@gmail.com", "nishant@123");
		CustomUserDetails obj1 = new CustomUserDetails(userObj);
		String name = obj1.getUsername();
		String pass = obj1.getPassword();
		assertEquals("nishant@gmail.com", name );
		assertEquals("nishant@123", pass);
	}
	
	@Test
	void testAuthorities() {
		User s = new User("nishant@gmail.com", "nishant@123");
		CustomUserDetails obj2 = new CustomUserDetails(s);
		Collection<?> GrantedAuth = obj2.getAuthorities();
		assertEquals(Collections.emptyList(), GrantedAuth);
	}
	
	@Test
	void testCreds() {
		User s = new User("nishant@gmail.com", "nishant@123");
		CustomUserDetails obj5 = new CustomUserDetails(s);
		boolean CredExpired = obj5.isCredentialsNonExpired();
		assertEquals(true, CredExpired);
	}
	
	@Test
	void testEnabled() {
		User s = new User("nishant@gmail.com", "nishant@123");
		CustomUserDetails obj6 = new CustomUserDetails(s);
		boolean Enabled = obj6.isEnabled();
		assertEquals(true, Enabled);
	}
	
	@Test
	void testExpired() {
		User s = new User("nishant@gmail.com", "nishant@123");
		CustomUserDetails obj3 = new CustomUserDetails(s);
		boolean Expired = obj3.isAccountNonExpired();
		assertEquals(true, Expired);
	}
	
	@Test
	void testNonLocked() {
		User s = new User("nishant@gmail.com", "nishant@123");
		CustomUserDetails obj4 = new CustomUserDetails(s);
		boolean NonLocked = obj4.isAccountNonLocked();
		assertEquals(true, NonLocked);
	}

}
