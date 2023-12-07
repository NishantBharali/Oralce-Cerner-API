package com.backend.dev;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.backend.dev.controller.IdeaController;
import com.backend.dev.controller.UserController;
import com.backend.dev.model.Idea;
import com.backend.dev.model.User;
import com.backend.dev.repositories.IdeaRepository;
import com.backend.dev.repositories.UserRepository;
import com.backend.dev.services.DatabaseService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
class IdeaController2Test {
	
	   @Autowired
	   private MockMvc mvc;
	   
	   @InjectMocks
	   IdeaController ideaController;
	   
	   @InjectMocks
	   UserController userController;
	   
	   @Mock
	   User user;
	   
	   @Mock
	   Idea idea;
	   
	   @MockBean
	   IdeaRepository ideaRepository;
	   
	   @MockBean
	   UserRepository userRepository;
	   
	   @MockBean
	   DatabaseService databaseService;

	   @Autowired
	   WebApplicationContext webApplicationContext;
	   
	   @BeforeEach
	   private void setUp() {
	      mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	   }

	@Test
	void postIdeasSuccessTest() throws Exception {
		Optional<User> user = Optional.ofNullable(new User("test3@gmail.com", "test3"));
		User user1 = new User();
		user1.setEmail("test3@gmail.com");
		user1.setPassword("test3");
		 Idea idea = new Idea(user1, "test", "test d", 3);
		 
		userController.setDatabaseService(databaseService);
		when(databaseService.getUserRepository()).thenReturn(userRepository); 
		ideaController.setDatabaseService(databaseService);
		when(databaseService.getIdeaRepository()).thenReturn(ideaRepository);
		when(userRepository.findByEmail("test3@gmail.com")).thenReturn(user);
		Mockito.when(ideaRepository.save(Mockito.any(Idea.class))).thenReturn(idea);
		
		 mvc.perform(post("/idea")
	    		   .param("email", user1.getEmail())
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
		void postIdeasFailureTest() throws Exception {
			Optional<User> user = Optional.ofNullable(new User("test3@gmail.com", "test3"));
			User user1 = new User();
			user1.setEmail("test3@gmail.com");
			user1.setPassword("test3");
			 Idea idea = new Idea(user1, "test", "test d", 3);
			 
			userController.setDatabaseService(databaseService);
			when(databaseService.getUserRepository()).thenReturn(userRepository); 
			ideaController.setDatabaseService(databaseService);
			when(databaseService.getIdeaRepository()).thenReturn(ideaRepository);
			when(userRepository.findByEmail("test3@gmail.com")).thenReturn(user);
			Mockito.when(ideaRepository.save(Mockito.any(Idea.class))).thenReturn(idea);
			
			 mvc.perform(post("/idea")
		    		   .param("email", user1.getEmail())
		     		  .content(asJsonString(null))
		     		  .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
		               .andDo(print())
		               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		               .andExpect(status().isIAmATeapot());
			
			
		}
	   
	 @Test
	   void getAuthenticatedUserIdeas() throws Exception {

			   Optional<User> user = Optional.ofNullable(new User("test", "test"));
			   User user1 = new User();
			   user1.setEmail("test");
			   user1.setPassword("test");
			   Optional<Idea> idea = Optional.ofNullable(new Idea(user1, "test", "test d", 3));
			   userController.setDatabaseService(databaseService);
			   when(databaseService.getUserRepository()).thenReturn(userRepository); 
			   ideaController.setDatabaseService(databaseService);
			   when(databaseService.getIdeaRepository()).thenReturn(ideaRepository);
			   
			   when(userRepository.findByEmail(user1.getEmail())).thenReturn(user);
			   when(ideaRepository.existsById(1l)).thenReturn(true);
			   when(ideaRepository.findById(1l)).thenReturn(idea);
		   
	       mvc.perform(get("/idea/user")
	    		   .param("email", user1.getEmail()))
	               .andDo(print())
	               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk());
	   }
	 
	 @Test
	   void getAuthenticatedUserIdeasFailureTest() throws Exception {

			   Optional<User> user = Optional.ofNullable(new User("test", "test"));
			   User user1 = new User();
			   user1.setEmail("test");
			   user1.setPassword("test");
			   Optional<Idea> idea = Optional.ofNullable(new Idea(user1, "test", "test d", 3));
			   userController.setDatabaseService(databaseService);
			   when(databaseService.getUserRepository()).thenReturn(userRepository); 
			   ideaController.setDatabaseService(databaseService);
			   when(databaseService.getIdeaRepository()).thenReturn(ideaRepository);
			   
			   when(userRepository.findByEmail(user1.getEmail())).thenReturn(user);
			   when(ideaRepository.existsById(1l)).thenReturn(true);
			   when(ideaRepository.findById(1l)).thenReturn(idea);
		   
	       mvc.perform(get("/idea/user")
	    		   .param("email", "email"))
	               .andDo(print())
	               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	               .andExpect(status().isNotFound());
	   }
	 
	   @Test
	   void getIdeasListTest() throws Exception {
		   
		   User user1 = new User();
		   user1.setEmail("test");
		   user1.setPassword("test");
		   
		   Idea IDEA_1 = new Idea(user1, "test1", "test1", 1);
		   Idea IDEA_2 = new Idea(user1, "test2", "test1", 2);
		   Idea IDEA_3 = new Idea(user1, "test3", "test1", 3);
		   
		   java.util.List<Idea> idea = new ArrayList<>(java.util.Arrays.asList(IDEA_1, IDEA_2, IDEA_3));
		   
		  
		   ideaController.setDatabaseService(databaseService);
		   when(databaseService.getIdeaRepository()).thenReturn(ideaRepository);
		   
		   when(ideaRepository.findAll()).thenReturn(idea);
		   
		   mvc.perform(get("/idea"))
	               .andDo(print())
	               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk());
	   }

}
