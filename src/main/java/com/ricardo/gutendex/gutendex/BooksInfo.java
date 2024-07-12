package com.ricardo.gutendex.gutendex;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ricardo.gutendex.model.BookDTO;
import com.ricardo.gutendex.service.AuthorService;
import com.ricardo.gutendex.service.BookService;
import com.ricardo.gutendex.service.RequestClient;
import com.ricardo.gutendex.util.JsonUtils;

@Component
public class BooksInfo {
	private RequestClient client = new RequestClient("https://gutendex.com/");
	private Scanner scanner = new Scanner(System.in);

	private BookService bookService;
	private AuthorService authorService;

	public BooksInfo(BookService bookService, AuthorService authorService) {
		this.bookService = bookService;
		this.authorService = authorService;
	}

	public void showMenu() throws UnsupportedEncodingException, JsonProcessingException {
		int option = -1;
		while (option != 0) {
			String menu = """
					1. Search a book.
					2. Display all books.
					3. Display all authors.
					4. Display alive authors before a year.

					0. Exit
					""";
			System.out.println(menu);
			try {
				System.out.print("Your option: ");
				option = scanner.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid option!");
				continue;
			} finally {
				scanner.nextLine();
			}

			switch (option) {
				case 1:
					searchBook();
					break;
				case 2:
					showBooks();
					break;
				case 3:
					showAuthors();
					break;
				case 4:
					showAliveAuthors();
					break;
				case 0:
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Unknown option.");
					break;
			}
		}
	}

	public void searchBook() throws UnsupportedEncodingException, JsonProcessingException {
		System.out.print("Enter a book name to search: ");
		String bookName = scanner.nextLine();
		String result = this.client.get("books/?search=" + URLEncoder.encode(bookName, "UTF-8"));
		List<BookDTO> booksResult = JsonUtils.extractListFromJson(result, BookDTO.class, "results");
		Optional<BookDTO> bookData = booksResult.stream().findFirst();

		if (bookData.isPresent()) {
			System.out.println("Book found!");
			bookService.save(bookData.get());
		} else {
			System.out.println("Book not found");
		}
	}

	public void showBooks() {
		bookService.getAllBooks();
	}

	private void showAuthors() {
		authorService.getAllAuthorsWithBooks();
	}

	private void showAliveAuthors() {
		System.out.print("Enter a year to search: ");
		var year = scanner.nextInt();
		scanner.nextLine();

		authorService.getAliveAuthors(year);
	}
}
