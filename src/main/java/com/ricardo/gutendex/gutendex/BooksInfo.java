package com.ricardo.gutendex.gutendex;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ricardo.gutendex.model.Book;
import com.ricardo.gutendex.model.BookDTO;
import com.ricardo.gutendex.service.RequestClient;
import com.ricardo.gutendex.util.JsonUtils;

public class BooksInfo {
	private RequestClient client = new RequestClient("https://gutendex.com/");
	private Scanner scanner = new Scanner(System.in);
	private List<Book> books = new ArrayList<>();

	public void showMenu() throws UnsupportedEncodingException, JsonProcessingException {
		int option = -1;
		while (option != 0) {
			String menu = """
					1. Search a book.
					2. Show books.

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
			Book book = new Book(bookData.get());
			books.add(book);
			System.out.println(book);
		} else {
			System.out.println("Book not found");
		}
	}

	public void showBooks() {
		if (!books.isEmpty()) {
			System.out.println("The available books are:");
			books.forEach(System.out::println);
		} else {
			System.out.println("No available books.");
		}
	}
}
