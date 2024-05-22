package com.ricardo.gutendex.gutendex;

import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ricardo.gutendex.model.BooksData;
import com.ricardo.gutendex.model.Book;
import com.ricardo.gutendex.service.DataConverter;
import com.ricardo.gutendex.service.RequestClient;

public class BooksInfo {
	RequestClient client = new RequestClient("https://gutendex.com/");
	DataConverter dataConverter = new DataConverter();
	BooksData data;

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
}
