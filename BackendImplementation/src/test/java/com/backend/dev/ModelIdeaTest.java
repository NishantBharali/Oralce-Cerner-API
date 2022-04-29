package com.backend.dev;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.dev.model.Idea;
import com.backend.dev.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
class ModelIdeaTest {
	
		@SuppressWarnings("unlikely-arg-type")
		@Test
		void modelIdeaTest() {
			Idea mainIdea = new Idea();
		    Idea ideaObj = new Idea(null, "title", "description", 1);
			Idea ideaObj1 = new Idea(null, "title", "description", 1);
			mainIdea.setId(3);
			ideaObj.setId(2);
			ideaObj1.setId(2);
			long idContainer = mainIdea.getId();
			long idContainer1 = ideaObj.getId();
			long idContainer2 = ideaObj1.getId();
			mainIdea.setUser(null);
			
			assertEquals(3, idContainer);
			assertEquals(null, mainIdea.getUser());
			assertEquals(ideaObj.hashCode(), ideaObj1.hashCode());
			assertEquals(ideaObj1, ideaObj);
			assertEquals(ideaObj, ideaObj1);
			assertEquals(idContainer1, idContainer2);
			assertEquals(idContainer2, idContainer1);
			assertEquals(true, ideaObj.equals(ideaObj));
			assertEquals(false, ideaObj.equals(null));
			assertEquals(false, ideaObj.equals(ideaObj.getClass()));
		}
		
		
		@Test
		void testUserToString() {
			Idea mainObjIdea = new Idea(null, "title", "description", 1);
			Idea toStringObj = new Idea(null, "title", "description", 1);
			User user = toStringObj.getUser();
			String title = toStringObj.getIdeaTitle();
			String description = toStringObj.getIdeaDescription();
			int storypoints = toStringObj.getIdeaStorypoints();
			assertEquals("Idea [email=" + user + ", ideaTitle=" + title + ", ideaDescription=" + description
					+ ", ideaStorypoints=" + storypoints + "]", mainObjIdea.toString());
		}
		

	}


