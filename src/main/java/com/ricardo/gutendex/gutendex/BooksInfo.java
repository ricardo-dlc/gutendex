package com.ricardo.gutendex.gutendex;

import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.IntSummaryStatistics;

import com.ricardo.gutendex.model.BooksData;
import com.ricardo.gutendex.model.Book;
import com.ricardo.gutendex.service.DataConverter;
import com.ricardo.gutendex.service.RequestClient;

public class BooksInfo {
	RequestClient client = new RequestClient("https://gutendex.com/");
	DataConverter dataConverter = new DataConverter();
	BooksData data;
	Scanner scanner = new Scanner(System.in);

	public void init() {
		String result = this.client.get("books/");
		this.data = dataConverter.getData(result, BooksData.class);
		// System.out.println(result);
		// System.out.println(this.data);
	}

	public void topNBooks(int limit) {
		System.out.println("Displaying the top " + limit + " books.");
		this.data.books().stream()
				.sorted(Comparator.comparing(Book::downloadCount).reversed())
				.limit(limit)
				.map(b -> b.title())
				.forEach(System.out::println);
	}

	public void summary() {
		IntSummaryStatistics stats = this.data.books().stream()
				.filter(b -> b.downloadCount() > 0)
				.collect(Collectors.summarizingInt(Book::downloadCount));
		System.out.println("Average downloads: " + stats.getAverage());
		System.out.println("Max downloads: " + stats.getMax());
		System.out.println("Min downloads: " + stats.getMin());
		System.out.println("Total books evaluated: " + stats.getCount());
	}

	public void searchBook() throws UnsupportedEncodingException {
		System.out.print("Enter a book name to search: ");
		String bookName = scanner.nextLine();
		String result = this.client.get("books/?search=" + URLEncoder.encode(bookName, "UTF-8"));
		BooksData data = dataConverter.getData(result, BooksData.class);

		Optional<Book> book = data.books().stream().findFirst();

		if (book.isPresent()) {
			System.out.println("Book found!");
			System.out.println(book.get());
		} else {
			System.out.println("Book not found");
		}
	}

	public void searchBookBetweenYears() throws UnsupportedEncodingException {
		System.out.print("Enter a start year: ");
		Integer startYear = Integer.valueOf(scanner.nextLine());
		System.out.print("Enter an end year: ");
		Integer endYear = Integer.valueOf(scanner.nextLine());

		String result = this.client
				.get("books/?author_year_start=" + startYear + "&author_year_end=" + endYear);
		BooksData searchResult = dataConverter.getData(result, BooksData.class);

		searchResult.books().stream().map(b -> b.title()).forEach(System.out::println);
	}
}
