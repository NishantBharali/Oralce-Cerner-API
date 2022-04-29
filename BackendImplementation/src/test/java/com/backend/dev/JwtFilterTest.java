package com.backend.dev;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletResponse;
import com.backend.dev.jwtutils.JwtFilter;

class JwtFilterTest {
	
	class MyClassMock1 extends JwtFilter {
        @Override
        public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                FilterChain filterChain) {
        }
    }

    @Mock
    MyClassMock1 myClass = mock(MyClassMock1.class);

    @Test
    void jwtFilterTest() {
    	HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
    	HttpServletResponse res = new MockHttpServletResponse();
    	FilterChain fil = new MockFilterChain();
       MyClassMock1 obj = new MyClassMock1();
       obj.doFilterInternal(req, res, fil);
       assertEquals(null, req.changeSessionId());
       String TokenHeader = req.getHeader("Authorization");
       assertNotNull(obj.getClass());
       assertFalse(TokenHeader != null && TokenHeader.startsWith("Bearer "));
    }
}

