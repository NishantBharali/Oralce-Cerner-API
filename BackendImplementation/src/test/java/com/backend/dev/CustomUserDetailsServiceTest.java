package com.backend.dev;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.dev.jwtutils.JwtFilter;
import com.backend.dev.model.User;
import com.backend.dev.repositories.UserRepository;
import com.backend.dev.security.CustomUserDetails;
import com.backend.dev.security.CustomUserDetailsService;
import com.backend.dev.services.DatabaseService;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomUserDetailsServiceTest {
	
	@MockBean
	UserRepository userRepository;
	
	@MockBean
	UserDetails userDetails;
	
	@MockBean
	DatabaseService databaseService;
	

	@Test
	void test() {
		User userObj = new User("nishant@gmail.com", "nishant@123");
		CustomUserDetails custObj = new CustomUserDetails(userObj);
		CustomUserDetailsService obj = new CustomUserDetailsService();
		assertEquals(userObj.getEmail(), custObj.getUsername());
		assertNotNull(obj.getClass());
	}

	class MyClassMock extends JwtFilter {
        @Override
        public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                FilterChain filterChain) {
        }
    }

    @Mock
    MyClassMock myClass = mock(MyClassMock.class);

    @Test
    void myClassPublicMethodTest() {
    	HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
    	HttpServletResponse res = new MockHttpServletResponse();
    	FilterChain fil = new MockFilterChain();
       MyClassMock obj = new MyClassMock();
       obj.doFilterInternal(req, res, fil);
       assertEquals(null, req.changeSessionId());
    }
}
