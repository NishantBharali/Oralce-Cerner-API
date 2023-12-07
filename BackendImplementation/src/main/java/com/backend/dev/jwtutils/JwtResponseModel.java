package com.backend.dev.jwtutils;

import java.io.Serializable;

/**
 * The Class JwtResponseModel.
 */
public class JwtResponseModel implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	private final String token;

    /**
     * Instantiates a new jwt response model.
     *
     * @param token the token
     */
    public JwtResponseModel(String token) {
        this.token = token;
    }

    /**
     * Gets the token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }
}