package com.ricardo.gutendex.gutendex;

import com.ricardo.gutendex.service.DataConverter;
import com.ricardo.gutendex.service.RequestClient;

public class Main {
	public static void main() {
		RequestClient client = new RequestClient("https://gutendex.com/");
		DataConverter dataConverter = new DataConverter();

		String books = client.get("books/");
		System.out.println(books);
	}
}
