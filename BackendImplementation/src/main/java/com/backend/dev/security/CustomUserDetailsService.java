package com.backend.dev.security;

import com.backend.dev.model.*;

import com.backend.dev.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The Class CustomUserDetailsService.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DatabaseService databaseService;
    
	public DatabaseService getDatabaseService() {
		return databaseService;
	}

	public void setDatabaseService(DatabaseService databaseService) {
		this.databaseService = databaseService;
	}

    /**
     * Load user by username.
     *
     * @param email the String email to load username
     * @return the user details
     * @throws UsernameNotFoundException the username not found exception is thrown
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = databaseService.getUserRepository().findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found user : " + email));
        return user.map(CustomUserDetails::new).get();
    }
}