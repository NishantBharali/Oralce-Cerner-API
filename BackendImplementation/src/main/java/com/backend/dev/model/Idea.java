package com.backend.dev.model;

import java.util.Objects;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
 

/**
 * The Class Idea.
 */
@JsonSerialize
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "ideas")
public class Idea {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idea_id", nullable = false)
    private long id;
 
    @Column(name = "idea_title", nullable = false, length = 100)
    private String ideaTitle;
 
    @Column(name = "idea_description", nullable = false, length = 3000)
    private String ideaDescription;
 
    @Column(name = "idea_storypoints", nullable = false)
    private int ideaStorypoints;
 
    @ManyToOne(optional = false, fetch = FetchType.LAZY,  cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH} )
    @JoinColumn(name = "email_id")
    private User user;
 
    /**
     * Instantiates a new idea.
     */
    public Idea() {
    }
 
    /**
     * Instantiates a new idea with parameters.
     *
     * @param user the user
     * @param ideaTitle the idea title
     * @param ideaDescription the idea description
     * @param ideaStorypoints the idea story points
     */
    public Idea(User user, String ideaTitle, String ideaDescription, int ideaStorypoints) {
        this.user = user;
    	this.ideaTitle = ideaTitle;
        this.ideaDescription = ideaDescription;
        this.ideaStorypoints = ideaStorypoints;
    }
 
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdeaTitle() {
		return ideaTitle;
	}

	public void setIdeaTitle(String ideaTitle) {
		this.ideaTitle = ideaTitle;
	}

	public String getIdeaDescription() {
		return ideaDescription;
	}

	public void setIdeaDescription(String ideaDescription) {
		this.ideaDescription = ideaDescription;
	}

	public int getIdeaStorypoints() {
		return ideaStorypoints;
	}

	public void setIdeaStorypoints(int ideaStorypoints) {
		this.ideaStorypoints = ideaStorypoints;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
        return user;
    }
 
    /**
     * Sets the user.
     *
     * @param user the new user
     */
    public void setUser(User user) {
        this.user = user;
    }

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Idea other = (Idea) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Idea [email=" + user + ", ideaTitle=" + ideaTitle + ", ideaDescription=" + ideaDescription
				+ ", ideaStorypoints=" + ideaStorypoints + "]";
	}
}

	