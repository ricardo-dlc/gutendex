package com.ricardo.gutendex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ricardo.gutendex.model.Author;
import com.ricardo.gutendex.repository.AuthorRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthorService {
	private AuthorRepository authorRepository;

	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Transactional
	public void getAllAuthorsWithBooks() {
		List<Author> authors = authorRepository.findAll();
		if (!authors.isEmpty()) {
			for (Author author : authors) {
				System.out.println("Author: " + author.getName());
				author.getBooks().forEach(book -> System.out.println("\t- Book: " + book.getTitle()));
			}
		} else {
			System.out.println("No authors found.");
		}
	}
}
