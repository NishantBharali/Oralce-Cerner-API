package com.backend.dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.dev.model.*;

/**
 * The Interface IdeaRepository.
 */
@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {


}