package com.ricardo.gutendex.model;

public class Book {
	private String title;
	private Author author;
	private String language;
	private Integer downloadCount;

	public Book(BookDTO data) {
		this.title = data.title();
		this.author = new Author(data.authors().get(0));
		this.language = data.languages().get(0);
		this.downloadCount = data.downloadCount();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", language=" + language + ", downloadCount="
				+ downloadCount + "]";
	}

}
