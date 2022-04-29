package com.backend.dev;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.dev.jwtutils.JwtResponseModel;
import com.backend.dev.services.DatabaseService;

@RunWith(SpringRunner.class)
@SpringBootTest
class JwtResponseModelTest {

	@Mock
	DatabaseService service;
	
	@Test
	void testJwtResponsModel() {
		JwtResponseModel obj = new JwtResponseModel("test Token");
		String testToken = obj.getToken();
		assertEquals("test Token", testToken);
	}

	@Test
	void test() {
		assertEquals(null, service.getUserRepository()); 
		assertEquals(null, service.getIdeaRepository());
	}

}
