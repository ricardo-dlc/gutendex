package com.ricardo.gutendex.model;

public class Author {
	private String name;
	private Integer birthYear;
	private Integer deathYear;

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
