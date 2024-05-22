package com.ricardo.gutendex.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Author(
		String name,
		@JsonAlias("birth_year") int birthYear,
		@JsonAlias("death_year") int deathYear) {

}
