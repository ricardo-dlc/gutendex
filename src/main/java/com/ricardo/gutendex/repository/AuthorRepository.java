package com.ricardo.gutendex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricardo.gutendex.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	boolean existsByName(String name);

	Author getReferenceByName(String authorName);

	List<Author> findByDeathYearLessThanEqual(int year);

}
