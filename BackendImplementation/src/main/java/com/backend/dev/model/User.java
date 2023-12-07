package com.backend.dev.model;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;

import javax.persistence.OneToMany;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 
/**
 * The Class User.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "users")
public class User {
 
    @Id
    @Column(name = "email_id", nullable = false, length = 30, unique = true)
    private String email;
    
    @Column(name = "user_password", nullable = false, length = 100)
    private String password;
    
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Idea> ideas;

    /**
     * Instantiates a new user.
     */
    public User() {
 
    }
 
    /**
     * Instantiates a new user with parameters.
     *
     * @param email the email
     * @param password the password
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.ideas = new ArrayList<>();
    }
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
 	/**
	  * Gets the ideas.
	  *
	  * @return the ideas
	  */
	 public List<Idea> getIdeas() {
		return ideas;
	}

	/**
	 * Sets the ideas.
	 *
	 * @param ideas the new ideas
	 */
	public void setIdeas(List<Idea> ideas) {
		this.ideas = ideas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + "]";
	}

	
}
