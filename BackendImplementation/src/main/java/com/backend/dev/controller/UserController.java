package com.backend.dev.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dev.errors.ErrorResponse;
import com.backend.dev.model.*;

import com.backend.dev.repositories.UserRepository;
import com.backend.dev.services.DatabaseService;

/**
 * The Class UserController.
 * 
 * Contains method to add a user and save the data in the repository
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public DatabaseService databaseService;

	@Autowired
    private PasswordEncoder passwordEncoder;

	Logger log = LoggerFactory.getLogger(UserController.class);

	public DatabaseService getDatabaseService() {
		return databaseService;
	}

	public void setDatabaseService(DatabaseService databaseService) {
		this.databaseService = databaseService;
	}
	
	/**
	 * Adds a user and save the data in the repository
	 *
	 * @param user - variable of User class
	 * @return the response entity
	 */
	@PostMapping("/user")
	public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
		
		log.debug("Entered addUser of UserController class");
		
		Optional<User> details = userRepository.findByEmail(user.getEmail());
		if(details.isPresent()) {
			log.debug("Checking that a user already exists");
			return new ResponseEntity<>(new ErrorResponse(400, "User already exists, please try a new username"), HttpStatus.BAD_REQUEST);
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		log.debug("exiting addUser method");
		return new ResponseEntity<>(new ErrorResponse(200, "User Registered Successfully"), HttpStatus.OK);
		
		
	}

}
