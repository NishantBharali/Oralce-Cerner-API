package com.backend.dev.jwtutils;

import java.io.Serializable;

/**
 * The Class JwtRequestModel.
 */
public class JwtRequestModel implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	private String username;
    
    private String password;
    
    /**
     * Instantiates a new jwt request model.
     */
    public JwtRequestModel() {
    }
    
    /**
     * Instantiates a new jwt request model.
     *
     * @param username the username
     * @param password the password
     */
    public JwtRequestModel(String username, String password) {
        super();
        this.username = username; this.password = password;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}