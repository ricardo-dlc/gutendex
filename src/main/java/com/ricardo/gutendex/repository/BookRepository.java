package com.ricardo.gutendex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricardo.gutendex.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	boolean existsByTitle(String title);

}
