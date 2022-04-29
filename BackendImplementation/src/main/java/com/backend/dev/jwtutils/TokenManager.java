package com.backend.dev.jwtutils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class TokenManager.
 */
@Component
public class TokenManager implements Serializable {
    /** 
     *
     */
	private static final long serialVersionUID = 1L;

	/** The Constant TOKEN_VALIDITY. */
	public static final long TOKEN_VALIDITY = 1000 * 60 * 60;

    /** The jwt secret. */
    @Value("${secret}")
    private String jwtSecret;
    
    public void setJwt() {
    	jwtSecret = "BackImplementation";
    }
    
    public String getSecret() {
    	return jwtSecret;
    }

    /**
     * Generate jwt token.
     *
     * @param userDetails the user details
     * @return the string
     */
    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }
    
    /**
     * Validates jwt token.
     *
     * @param token the token
     * @param userDetails the user details
     * @return the boolean
     */
    public Boolean validateJwtToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        Boolean isTokenExpired = claims.getExpiration().before(new Date());
        return (username.equals(userDetails.getUsername()) && !isTokenExpired);
    }
    
    /**
     * Gets the username from token.
     *
     * @param token the token
     * @return the username from token
     */
    public String getUsernameFromToken(String token) {
        final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}