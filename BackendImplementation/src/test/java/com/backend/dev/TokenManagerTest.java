package com.backend.dev;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.dev.jwtutils.TokenManager;
import com.backend.dev.model.User;
import com.backend.dev.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RunWith(SpringRunner.class)
@SpringBootTest
class TokenManagerTest {
	
	@Test
	void test() {
		TokenManager tmObj = new TokenManager();
		User userObj = new User("nishant@gmail.com", "nish@123");
		CustomUserDetails userDetailsObj = new CustomUserDetails(userObj);
		tmObj.setJwt();
		String str = tmObj.generateJwtToken(userDetailsObj);
		assertNotNull(str);
		assertEquals("nishant@gmail.com", tmObj.getUsernameFromToken(str));
		
	}
	
	@Test
	void validateTokenTest() {
			TokenManager tmObj1 = new TokenManager();
			User userObj1 = new User("test@gmail.com", "test@123");
			CustomUserDetails userDetailsObj1 = new CustomUserDetails(userObj1);
			tmObj1.setJwt();
			String str1 = tmObj1.generateJwtToken(userDetailsObj1);
			Claims claims = Jwts.parser().setSigningKey(tmObj1.getSecret()).parseClaimsJws(str1).getBody();
			Boolean isTokenExpired = claims.getExpiration().before(new Date());
			assertNotNull(str1);
			assertTrue(tmObj1.validateJwtToken(str1, userDetailsObj1));
			assertEquals(tmObj1.getUsernameFromToken(str1), userDetailsObj1.getUsername());
			assertTrue(tmObj1.getUsernameFromToken(str1).equals(userDetailsObj1.getUsername()) && !isTokenExpired);
			assertFalse(!tmObj1.getUsernameFromToken(str1).equals(userDetailsObj1.getUsername()) && isTokenExpired);
			assertFalse(isTokenExpired);
		}

}
