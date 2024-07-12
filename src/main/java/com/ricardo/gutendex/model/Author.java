package com.ricardo.gutendex.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer birthYear;
	private Integer deathYear;

	public Author() {
	}

	public Author(AuthorDTO data) {
		this.name = data.name();
		this.birthYear = data.birthYear();
		this.deathYear = data.deathYear();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	public Integer getDeathYear() {
		return deathYear;
	}

	public void setDeathYear(Integer deathYear) {
		this.deathYear = deathYear;
	}

	@Override
	public String toString() {
		return "Author [name=" + name + ", birthYear=" + birthYear + ", deathYear=" + deathYear + "]";
	}

}
