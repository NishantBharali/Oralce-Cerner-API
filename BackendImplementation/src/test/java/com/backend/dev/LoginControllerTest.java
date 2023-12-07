package com.backend.dev;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.backend.dev.controller.LoginController;
import com.backend.dev.jwtutils.JwtRequestModel;
import com.backend.dev.jwtutils.JwtResponseModel;
import com.backend.dev.jwtutils.TokenManager;
import com.backend.dev.repositories.UserRepository;
import com.backend.dev.security.CustomUserDetailsService;
import com.backend.dev.services.DatabaseService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
class LoginControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@InjectMocks
	private LoginController loginController;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private DatabaseService databaseService;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
    @Mock
    private CustomUserDetailsService userDetailsService;

    @Mock
    private AuthenticationManager authenticationManager;
    
    @Mock
    private UserDetails userDetails;
    
    @Mock
    private UserDetailsService service;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
    @Mock
    JwtRequestModel jwtRequestModel;
    
    @Mock
    JwtResponseModel jwtResponseModel;
    
    @Mock
    TokenManager tokenManager;
	   
	
	  @BeforeEach
	   private void setUp() {
	      mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	   }

	@Test
	void loginSuccessTest() throws Exception {
		
		jwtRequestModel.setUsername("test");
		jwtRequestModel.setPassword("test");
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequestModel.getUsername(), jwtRequestModel.getPassword()));
		Mockito.when(service.loadUserByUsername(jwtRequestModel.getUsername())).thenReturn(userDetails);
		@SuppressWarnings("unused")
		String jwtToken = tokenManager.generateJwtToken(userDetails);
		
		
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		ResponseEntity<?> responseEntity = loginController.createToken(jwtRequestModel);
		
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertEquals(responseEntity.getStatusCode(), (HttpStatus.OK));
		
	}

}
