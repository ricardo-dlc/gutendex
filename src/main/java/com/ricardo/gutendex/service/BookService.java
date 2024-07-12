package com.ricardo.gutendex.service;

import java.util.List;
import java.util.Scanner;

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
		var authorName = bookDTO.authors().get(0).name();
		var authorExists = authorRepository.existsByName(authorName);
		var bookExists = bookRepository.existsByTitle(bookDTO.title());
		if (bookExists && authorExists) {
			System.out.println("This book already exists.");
			return;
		}

		Author author;
		if (authorExists) {
			author = authorRepository.getReferenceByName(authorName);
		} else {
			author = new Author(bookDTO.authors().get(0));
			authorRepository.save(author);
		}
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

	public List<String> getLanguages() {
		return bookRepository.findAllLanguages();
	}

	public void getBooksByLanguage(Scanner scanner) {
		var languages = getLanguages();
		if (languages.isEmpty()) {
			System.out.println("No books or languages are available.");
			return;
		}

		System.out.println("Available languages are:");
		languages.stream().map(l -> l.toUpperCase()).forEach(System.out::println);
		System.out.print("Type a language from above: ");
		var lan = scanner.nextLine();
		System.out.println("You chose " + lan);
		var books = bookRepository.findByLanguageEqualsIgnoreCase(lan);
		if (books.isEmpty()) {
			System.out.println("No books found for this language.");
		} else {
			books.forEach(System.out::println);
		}
	}

}
