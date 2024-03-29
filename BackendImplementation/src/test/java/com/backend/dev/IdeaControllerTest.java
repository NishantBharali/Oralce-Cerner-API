package com.backend.dev;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Optional;

import org.hamcrest.Matchers;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.backend.dev.controller.IdeaController;
import com.backend.dev.errors.InvalidEndpointException;
import com.backend.dev.model.Idea;
import com.backend.dev.model.User;
import com.backend.dev.repositories.IdeaRepository;
import com.backend.dev.services.DatabaseService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
class IdeaControllerTest {
	
	@InjectMocks
	IdeaController ideaController;
	
   @Autowired
   private MockMvc mvc;
   
   @Mock
   User user;
   
   @Mock
   Idea idea;
   
   @MockBean
   IdeaRepository ideaRepository;
   
   @MockBean
   DatabaseService databaseService;
   
   @Autowired
   WebApplicationContext webApplicationContext;
   
   @BeforeEach
   private void setUp() {
      mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
   }
   
   @Test
   void databaseServiceGetterTest() {
	   ideaController.setDatabaseService(databaseService);
	   when(databaseService.getIdeaRepository()).thenReturn(ideaRepository);
	   assertNotNull(ideaController.getDatabaseService());
   }
   
   @Test
   void findIdeaByIdTestSuccess() throws Exception {

	   User user = new User("test", "test");
	   Optional<Idea> idea = Optional.ofNullable(new Idea(user, "test", "test", 3));
	   when(databaseService.getIdeaRepository()).thenReturn(ideaRepository);
	   when(ideaRepository.existsById(1l)).thenReturn(true);
	   when(ideaRepository.findById(1l)).thenReturn(idea);
	   
	   ResultActions response = mvc.perform(get("/idea/{id}", 1l));
	   
	   response.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.ideaTitle", Matchers.is("test")));
   }
   
   @Test
   void findIdeaByIdTestFailure() throws Exception {

	   User user = new User("test", "test");
	   Optional<Idea> idea = Optional.ofNullable(new Idea(user, "test", "test", 3));
	   when(databaseService.getIdeaRepository()).thenReturn(ideaRepository);
	   when(ideaRepository.existsById(1l)).thenReturn(false);
	   when(ideaRepository.findById(1l)).thenReturn(idea);
	   
	   ResultActions response = mvc.perform(get("/idea/{id}", 1l));
	   
	   response.andDo(print()).andExpect(status().isNotFound());
   }

   		public String asJsonString(final Object obj) {
   			try {
   				return new ObjectMapper().writeValueAsString(obj);
   			} catch (Exception e) {
   				throw new RuntimeException(e);
   			}
   		}
   
   	  @Test
   	  void updateSuccessTest() throws Exception {
    	   
   	 	  User user = new User("dhruv@gmail.com", "DHruv@2009");
   	 	 Optional<Idea> idea1 = Optional.ofNullable(new Idea(user, "test", "test d", 3));
   	 	 Idea idea2 = new Idea(user, "test is new", "test description is new", 4);
   	 	 ideaController.setDatabaseService(databaseService);
   		 when(databaseService.getIdeaRepository()).thenReturn(ideaRepository);
   	 	  Mockito.when(ideaRepository.existsById(1l)).thenReturn(true);
   	 	  Mockito.when(ideaRepository.findById(1l)).thenReturn(idea1);
   	 	  Mockito.when(ideaRepository.save(idea2)).thenReturn(idea2);
   	 	  	   
   	       mvc.perform(put("/idea/{id}", 1l)
   	     		  .content(asJsonString(idea2))
   	     		  .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
   	               .andDo(print())
   	               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
   	               .andExpect(status().isOk())
   	       		   .andExpect(jsonPath("$.ideaTitle", is("test is new")))
   	       		   .andExpect(jsonPath("$.ideaDescription", is("test description is new")))
   	       		   .andExpect(jsonPath("$.ideaStorypoints", is(4)));
   	         
   	   }
   
   @Test
   void deleteIdeaSuccessTest() throws Exception {
	   long id = 1l;
	   when(databaseService.getIdeaRepository()).thenReturn(ideaRepository);
	   when(ideaRepository.existsById(id)).thenReturn(true);
	   doNothing().when(ideaRepository).deleteById(id);
	   
	   ResultActions response = mvc.perform(delete("/idea/{id}", id));
	   
	   response.andExpect(status().isOk()).andExpect(jsonPath("$.status", is(200)))
	   .andExpect(jsonPath("$.message", is("idea deleted")));
	   
   }
   
   @Test
   void deleteIdeaErrorTest() throws Exception {
	   long id = 1l;
	ideaController.setDatabaseService(databaseService);
	when(databaseService.getIdeaRepository()).thenReturn(ideaRepository);
	when(ideaRepository.existsById(id)).thenReturn(false);
	InvalidEndpointException endpointException = assertThrows(InvalidEndpointException.class, ()->{
		ideaController.deleteIdea(id);
		});
	assertEquals("id not found", endpointException.getMessage());
	verify(databaseService.getIdeaRepository(), never()).deleteById(anyLong());
   }
   
   @Test
   void updateIdeaErrorTest() throws Exception {
	   long id = 1l;
	   Idea idea = new Idea(user, "test", "test d", 3);
	ideaController.setDatabaseService(databaseService);
	when(databaseService.getIdeaRepository()).thenReturn(ideaRepository);
	when(ideaRepository.existsById(id)).thenReturn(false);
	InvalidEndpointException endpointException = assertThrows(InvalidEndpointException.class, ()->{
		ideaController.editIdea(id, idea);
		});
	assertTrue(endpointException.getMessage().equals("id not found"));
	verify(databaseService.getIdeaRepository(), never()).save(idea);
   }
   
}
