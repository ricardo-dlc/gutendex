package com.ricardo.gutendex.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
		String title,
		List<AuthorDTO> authors,
		List<String> languages,
		@JsonAlias("download_count") int downloadCount) {

}
