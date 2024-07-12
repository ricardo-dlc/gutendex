package com.ricardo.gutendex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ricardo.gutendex.gutendex.BooksInfo;

@SpringBootApplication
public class GutendexApplication implements CommandLineRunner {

	private final BooksInfo booksInfo;

	// @Autowired
	public GutendexApplication(BooksInfo booksInfo) {
		this.booksInfo = booksInfo;
	}

	public static void main(String[] args) {
		SpringApplication.run(GutendexApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		booksInfo.showMenu();
	}

}
