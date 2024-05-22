package com.ricardo.gutendex.gutendex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GutendexApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GutendexApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		BooksInfo main = new BooksInfo();
		main.init();
		main.topNBooks(5);
	}

}
