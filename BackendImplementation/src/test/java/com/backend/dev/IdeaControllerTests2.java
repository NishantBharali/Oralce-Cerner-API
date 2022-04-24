package com.backend.dev;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.backend.dev.controller.IdeaController;
import com.backend.dev.errors.InvalidEndpointException;
import com.backend.dev.jwtutils.TokenManager;
import com.backend.dev.model.Idea;
import com.backend.dev.model.User;
import com.backend.dev.repositories.IdeaRepository;
import com.backend.dev.repositories.UserRepository;
import com.backend.dev.security.CustomUserDetails;
import com.backend.dev.services.DatabaseService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
class IdeaControllerTests2 {
		
   @Autowired
   private MockMvc mvc;
   
   @Mock
   User user;
   
   @Mock
   Idea idea;
   
   @MockBean
   IdeaRepository ideaRepository;

   @Autowired
   WebApplicationContext webApplicationContext;
   
   @BeforeEach
   private void setUp() {
      mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
   }
   
   private <T> T mapFromJson(String json, Class<T> clazz)
		      throws JsonParseException, JsonMappingException, IOException {
		      
		      ObjectMapper objectMapper = new ObjectMapper();
		      return objectMapper.readValue(json, clazz);
		   }
		   
		   @Test
		   void getIdeasListTest() throws Exception {
		      String uri = "/idea";
		      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
		         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		      
		      int status = mvcResult.getResponse().getStatus();
		      assertEquals(200, status);
		      String content = mvcResult.getResponse().getContentAsString();
		      Idea[] idealist = mapFromJson(content, Idea[].class);
		   }
		   
		   @Test
		   void getOneIdeaTest() throws Exception {
		      String uri = "/idea/{id}";
		      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, 3)
		         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		    	           
		      int status = mvcResult.getResponse().getStatus();
		      assertEquals(404, status);
		      String content = mvcResult.getResponse().getContentAsString();
		   }
		   
		   @Test
		   void getIdeasOfUserTest() throws Exception {
				  String email = "test";
				  TokenManager tmObj = new TokenManager();
				  User userObj = new User("test", "test");
			   	  CustomUserDetails userDetailsObj = new CustomUserDetails(userObj);
				  tmObj.setJwt();
				String str = tmObj.generateJwtToken(userDetailsObj);
			   
				String uri = "/idea/user";
		      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).param("email", email).header("Authorization", "Bearer " + str)
		         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		    	      
		      int status = mvcResult.getResponse().getStatus();
		      assertEquals(404, status);
		      String content = mvcResult.getResponse().getContentAsString();
		   }
		   
		   @Test
		   void getAuthUserIdea() throws Exception {

				  String email = "dhruv@gmail.com";
			   
		       mvc.perform(get("/idea/user")
		    		   .param("email", email))
		               .andDo(print())
		               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		               .andExpect(status().isOk());
		   }
   

	   @Test
	   void postSuccessTest() throws Exception {
	 	   
	 	  User user = new User("dhruv@gmail.com", "DHruv@2009");
	 	  Idea idea = new Idea(user, "test", "test d", 3);
	 	  
	 	  Mockito.when(ideaRepository.save(Mockito.any(Idea.class))).thenReturn(idea);
	 	  	   
	       mvc.perform(post("/idea")
	    		   .param("email", user.getEmail())
	     		  .content(asJsonString(idea))
	     		  .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
	               .andDo(print())
	               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk());
	          
	   }
	   
	   public String asJsonString(final Object obj) {
  			try {
  				return new ObjectMapper().writeValueAsString(obj);
  			} catch (Exception e) {
  				throw new RuntimeException(e);
  			}
  		}
	   
	   @Test
	   void PostAnIdeaTestFail() throws Exception {
		  user.setEmail("test");
		  String U = user.getEmail();
		  String email = "dhruv@gmail.com";
		  Idea idea = new Idea();
		  String uri = "/idea";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("email", email)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(418, status);
	      String content = mvcResult.getResponse().getContentAsString();
	   }
	   
}
