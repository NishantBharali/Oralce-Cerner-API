package com.backend.dev.controller;

import com.backend.dev.jwtutils.JwtRequestModel;


import com.backend.dev.jwtutils.JwtResponseModel;
import com.backend.dev.jwtutils.TokenManager;
import com.backend.dev.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class LoginController.
 * 
 * Contains a method to create a JWT token for the authenticated user login
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

 
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;

    /**
     * Creates the token.
     *
     * @param requestModel variable of JWT Request model class
     * @return the response entity
     * @throws Exception the exception
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponseModel> createToken(@RequestBody JwtRequestModel requestModel) throws Exception {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestModel.getUsername(), requestModel.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(requestModel.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));
    }
}