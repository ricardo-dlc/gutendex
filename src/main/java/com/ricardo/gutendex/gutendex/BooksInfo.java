package com.ricardo.gutendex.gutendex;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ricardo.gutendex.model.BookDTO;
import com.ricardo.gutendex.model.BooksData;
import com.ricardo.gutendex.service.DataConverter;
import com.ricardo.gutendex.service.RequestClient;
import com.ricardo.gutendex.util.JsonUtils;

public class BooksInfo {
	RequestClient client = new RequestClient("https://gutendex.com/");
	DataConverter dataConverter = new DataConverter();
	Scanner scanner = new Scanner(System.in);

	public void showMenu() throws UnsupportedEncodingException, JsonProcessingException {
		int option = -1;
		while (option != 0) {
			String menu = """
					1. Search a book.

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
		List<BookDTO> books = JsonUtils.extractListFromJson(result, BookDTO.class, "results");
		Optional<BookDTO> book = books.stream().findFirst();

		if (book.isPresent()) {
			System.out.println("Book found!");
			System.out.println(book.get());
		} else {
			System.out.println("Book not found");
		}
	}

	// public void searchBookBetweenYears() throws UnsupportedEncodingException {
	// 	System.out.print("Enter a start year: ");
	// 	Integer startYear = Integer.valueOf(scanner.nextLine());
	// 	System.out.print("Enter an end year: ");
	// 	Integer endYear = Integer.valueOf(scanner.nextLine());

	// 	String result = this.client
	// 			.get("books/?author_year_start=" + startYear + "&author_year_end=" + endYear);
	// 	BooksData searchResult = dataConverter.getData(result, BooksData.class);

	// 	searchResult.books().stream().map(b -> b.title()).forEach(System.out::println);
	// }
}
