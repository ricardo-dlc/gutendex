package com.ricardo.gutendex.gutendex;

import com.ricardo.gutendex.model.BooksData;
import com.ricardo.gutendex.service.DataConverter;
import com.ricardo.gutendex.service.RequestClient;

public class Main {
	RequestClient client = new RequestClient("https://gutendex.com/");
	DataConverter dataConverter = new DataConverter();

	public void main() {
		String result = this.client.get("books/");
		BooksData data = dataConverter.getData(result, BooksData.class);
		// System.out.println(result);
		System.out.println(data);
	}
}
