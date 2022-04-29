package com.backend.dev.services;

import com.backend.dev.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * The Class DatabaseService.
 * 
 * Contains getters and setters to create a service for fetching data from respective repositories
 */
@Component
public class DatabaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IdeaRepository ideaRepository;


    /**
     * Gets the user repository.
     *
     * @return the user repository
     */
    public UserRepository getUserRepository() {
        return userRepository;
    }

    /**
     * Gets the idea repository.
     *
     * @return the idea repository
     */
    public IdeaRepository getIdeaRepository() {
        return ideaRepository;
    }

}