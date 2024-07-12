package com.ricardo.gutendex.service;

import org.springframework.stereotype.Service;

import com.ricardo.gutendex.model.Author;
import com.ricardo.gutendex.model.Book;
import com.ricardo.gutendex.model.BookDTO;
import com.ricardo.gutendex.repository.AuthorRepository;
import com.ricardo.gutendex.repository.BookRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService {

	private BookRepository bookRepository;
	private AuthorRepository authorRepository;

	public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}

	@Transactional
	public void save(BookDTO bookDTO) {
		if (bookRepository.existsByTitle(bookDTO.title())
				&& authorRepository.existsByName(bookDTO.authors().get(0).name())) {
			System.out.println("This book already exists.");
			return;
		}

		Author author = new Author(bookDTO.authors().get(0));
		authorRepository.save(author);
		Book book = new Book(bookDTO, author);
		bookRepository.save(book);
		System.out.println(book);
	}

	public void getAllBooks() {
		var books = bookRepository.findAll();

		if (books.isEmpty()) {
			System.out.println("No books available");
		} else {
			System.out.println("Books available are:");
			books.forEach(System.out::println);
		}
	}

}
