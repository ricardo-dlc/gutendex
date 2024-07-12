package com.ricardo.gutendex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ricardo.gutendex.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	boolean existsByTitle(String title);

	@Query("SELECT language FROM Book GROUP BY language")
	List<String> findAllLanguages();

	List<Book> findByLanguageEqualsIgnoreCase(String language);

}
