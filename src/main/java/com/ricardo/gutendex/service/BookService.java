package com.ricardo.gutendex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ricardo.gutendex.model.Author;
import com.ricardo.gutendex.model.Book;
import com.ricardo.gutendex.model.BookDTO;
import com.ricardo.gutendex.repository.AuthorRepository;
import com.ricardo.gutendex.repository.BookRepository;

@Service
public class BookService {

	private BookRepository bookRepository;
	private AuthorRepository authorRepository;

	public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}

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

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

}
