package com.backend.dev;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.backend.dev.model.User;
import com.backend.dev.repositories.UserRepository;
import com.backend.dev.security.CustomUserDetailsService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomUserDetailsService1Test {
	
	@Mock
	User user;
	
    @MockBean
    private UserRepository userRepository;

    private CustomUserDetailsService customUserDetailsService;

    @Before
    public void setUp() throws Exception {
        customUserDetailsService = new CustomUserDetailsService();
    }

    @Test
    void GIVEN_username_THEN_return_user_details() {
        
        final String username = "existingUserName";
        final Optional<User> user = Optional.ofNullable(new User());
        when(userRepository.findByEmail(username)).thenReturn(user);
        
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(user, ReflectionTestUtils.getField(userDetails, "user"));
    }
}