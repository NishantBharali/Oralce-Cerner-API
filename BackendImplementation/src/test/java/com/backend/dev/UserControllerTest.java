package com.backend.dev;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.IOException;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.backend.dev.controller.UserController;
import com.backend.dev.model.Idea;
import com.backend.dev.model.User;
import com.backend.dev.repositories.IdeaRepository;
import com.backend.dev.repositories.UserRepository;
import com.backend.dev.services.DatabaseService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class UserControllerTest {
	
	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private DatabaseService databaseService;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	
	@Before
	public void setup() {
		userController = new UserController();
		userController.setDatabaseService(databaseService);;
	}
	
	@Test
	public void addUserExistsTest() {
		User user = new User();
		user.setEmail("test3@gmail.com");
		user.setPassword("test3");
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional<User>);
     
        ResponseEntity<Object> responseEntity = userController.addUser(user);
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
        assertThat(responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST));
		

		
	}
	
	@Test
	public void addUserDoesntExistTest() {
		User user = new User();
		user.setEmail("");
		user.setPassword("test4");
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional<User>);
        when(passwordEncoder.encode(user.getPassword())).thenReturn(anyString());
        when(userRepository.save(user)).thenReturn(user);
        
        ResponseEntity<Object> responseEntity = userController.addUser(user);
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getStatusCode().equals(HttpStatus.OK));
		

		
	}
	
//	@Autowired
//	MockMvc mvc;
//
//	@Autowired
//	WebApplicationContext webApplicationContext;
//	
//	@MockBean
//	UserRepository userRepository;
//	
//	@MockBean
//	PasswordEncoder passwordEncoder;
//	
//	@Before
//	void setUp() throws Exception {
//		MockitoAnnotations.openMocks(this);
//		UserController controller = new UserController();
//		mvc = MockMvcBuilders.standaloneSetup(controller).build();
//	}
//	
////	 @BeforeEach
////	   private void setUp() {
////	      mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
////	   }
//	
//  @Test
//  void registrationTest() throws Exception {
//	   
//	  User user = new User("test3@gmail.com", "test3");
//	  
//	  Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
//	  	   
//      mvc.perform(post("/user")
//    		  .content(asJsonString(user))
//    		  .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
//              .andDo(print())
//              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//              .andExpect(status().isOk());
//         
//  }
//
//  		public static String asJsonString(final Object obj) {
//  			try {
//  				return new ObjectMapper().writeValueAsString(obj);
//  			} catch (Exception e) {
//  				throw new RuntimeException(e);
//  			}
//  		}
//  		
////  	  @Test
////  	  void registrationTestException() throws Exception {
////  		   
////  		  Optional<User> user = Optional.ofNullable(new User("test3@gmail.com", "test"));
////  		  Mockito.when(userRepository.findByEmail("test3@gmail.com")).thenReturn(user);
////  		  	   
////  	      mvc.perform(post("/user")
////  	    		  .content(asJsonString(user))
////  	    		  .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
////  	              .andDo(print())
////  	              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
////  	              .andExpect((ResultMatcher)status().isBadRequest());
////  	         
////  	  }
//  		
//  		
//  	   @Test
//  	   void registrationErrorTest() throws Exception {
//  		  User user = new User("test3@gmail.com", null);
//  	      String uri = "/user";
//  	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//  	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//  	      
//  	      int status = mvcResult.getResponse().getStatus();
//  	      assertEquals(418, status);
//  	      String content = mvcResult.getResponse().getContentAsString();
//  	   }

}
