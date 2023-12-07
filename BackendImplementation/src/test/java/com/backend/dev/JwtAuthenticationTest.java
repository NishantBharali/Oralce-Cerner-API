package com.backend.dev;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.backend.dev.jwtutils.JwtAuthenticationEntryPoint;

@RunWith(SpringRunner.class)
@SpringBootTest
class JwtAuthenticationTest {

	@Test
	void test() throws IOException, ServletException {
		HttpServletRequest resObj = new MockHttpServletRequest();
		HttpServletResponse respObj  = new MockHttpServletResponse();
		AuthenticationException authObj  = null;
		JwtAuthenticationEntryPoint obj = new JwtAuthenticationEntryPoint();
		
		obj.commence(resObj, respObj, authObj);
		assertNull(authObj);
	}

}
