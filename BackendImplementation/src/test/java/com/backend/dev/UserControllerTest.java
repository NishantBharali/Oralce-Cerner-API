package com.backend.dev;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.backend.dev.controller.UserController;
import com.backend.dev.model.User;
import com.backend.dev.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
class UserControllerTest {
	
	@Autowired
	MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;
	
	@MockBean
	UserRepository userRepository;
	
	@MockBean
	PasswordEncoder passwordEncoder;
	
	@Before
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		UserController controller = new UserController();
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	
  @Test
  void registrationTest() throws Exception {
	   
	  User user = new User("test3@gmail.com", "test3");
	  
	  Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
	  	   
      mvc.perform(post("/user")
    		  .content(asJsonString(user))
    		  .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
              .andDo(print())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk());
         
  }

  		public static String asJsonString(final Object obj) {
  			try {
  				return new ObjectMapper().writeValueAsString(obj);
  			} catch (Exception e) {
  				throw new RuntimeException(e);
  			}
  		}
  		
  	  @Test
  	  void registrationErrorTest() throws Exception {
  		   
  		  User user = new User("test3@gmail.com", null);
  		  
  		  Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
  		  	   
  	      mvc.perform(post("/user")
  	    		  .content(asJsonString(null))
  	    		  .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
  	              .andDo(print())
  	              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
  	              .andExpect(status().isIAmATeapot());
  	         
  	  }

}
