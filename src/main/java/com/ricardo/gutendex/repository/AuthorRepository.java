package com.ricardo.gutendex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricardo.gutendex.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	boolean existsByName(String name);

}
