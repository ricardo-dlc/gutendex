package com.ricardo.gutendex.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Book(
		// int id,
		String title,
		List<String> subjects,
		List<Author> authors,
		// List<AuthorRecord> translators,
		// List<String> bookshelves,
		List<String> languages,
		boolean copyright,
		@JsonAlias("media_type") String mediaType,
		// String formats,
		@JsonAlias("download_count") int downloadCount) {
}
