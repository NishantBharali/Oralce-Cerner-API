package com.backend.dev.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dev.errors.InvalidEndpointException;
import com.backend.dev.errors.SuccessResponse;
import com.backend.dev.model.Idea;
import com.backend.dev.model.User;
import com.backend.dev.services.DatabaseService;

/**
 * The Class IdeaController.
 * 
 * Contains methods to perform C.R.U.D operations on ideas by using REST Controller services
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class IdeaController {
	

	Logger log = LoggerFactory.getLogger(IdeaController.class);

	
	@Autowired
	DatabaseService databaseService;
	
	/**
	 * Retrieve all ideas irrespective of the user authenticated.
	 *
	 * @return the list of all Ideas
	 */
	@GetMapping("/idea")
	public List<Idea> retrieveAllIdeas(){
		
		return databaseService.getIdeaRepository().findAll();
	}
	
	/**
	 * Retrieve and view one idea based on their ID.
	 *
	 * @param id the id
	 * @return the idea
	 */
	@GetMapping("/idea/{id}")
	public Idea retrieveOneIdea(@PathVariable("id") long id) {
		
		 if (!databaseService.getIdeaRepository().existsById(id))
	        {
	            throw new InvalidEndpointException("id not found");
	        }
		 
	        return databaseService.getIdeaRepository().findById(id).get();
	    }
	
	/**
	 * Retrieve all ideas of user.
	 * 
	 * Requires authentication with access token
	 *
	 * @param email the email
	 * @return the list
	 */
	@GetMapping("/idea/user")
	public List<Idea> retrieveAllIdeasOfUser(@RequestParam String email) {
		
		 if (!databaseService.getUserRepository().findByEmail(email).isPresent())
	        {
	            throw new InvalidEndpointException("id not found");
	        }
		 else {
	        return databaseService.getUserRepository().findByEmail(email).get().getIdeas();
		 }
	    }
	
	/**
	 * Adds the idea.
	 *
	 * @param email the email
	 * @param idea the idea
	 * @return the idea - contains the entity elements of the Idea model
	 */
	@PostMapping("/idea")
	public Idea addIdea(@RequestParam String email,
						@RequestBody Idea idea)
	{
		User s =  databaseService.getUserRepository().findByEmail(email).get();
		log.debug(idea.toString());
		log.debug(email.toString()); 
		idea.setUser(s);
		databaseService.getIdeaRepository().save(idea);
		return idea;
	}
	
	/**
	 * Edits the idea.
	 *
	 * @param id the id
	 * @param idea the idea
	 * @return the idea
	 */
	@PutMapping("/idea/{id}")
    public Idea editIdea(@PathVariable long id, @RequestBody Idea idea)
    {
        if (!databaseService.getIdeaRepository().existsById(id))
        {
            throw new InvalidEndpointException("id not found");
        }
        Idea a = databaseService.getIdeaRepository().findById(id).get();
        a.setIdeaTitle(idea.getIdeaTitle());
        a.setIdeaDescription(idea.getIdeaDescription());
        a.setIdeaStorypoints(idea.getIdeaStorypoints());
        databaseService.getIdeaRepository().save(a);
        return idea;
    }
	
    /**
     * Deletes an idea.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/idea/{id}")
    public ResponseEntity<SuccessResponse> deleteIdea(@PathVariable long id)
    {
        if (!databaseService.getIdeaRepository().existsById(id))
        {
            throw new InvalidEndpointException("id not found");
        }
        databaseService.getIdeaRepository().deleteById(id);
        
        SuccessResponse sr = new SuccessResponse();
        sr.setStatus(HttpStatus.OK.value());
        sr.setMessage("idea deleted");

        return new ResponseEntity<>(sr, HttpStatus.OK);
    }

}
