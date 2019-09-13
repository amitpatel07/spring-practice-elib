package com.elibrary.data.view;

public class BookView {
	private Integer id;
	private String name;
	private String author;
	private Integer year;
	private String isbn;
	private String imagepath;
	private String storagepath;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public String getStoragepath() {
		return storagepath;
	}

	public void setStoragepath(String storagepath) {
		this.storagepath = storagepath;
	}

	@Override
	public String toString() {
		return "BookView [id=" + id + ", name=" + name + ", author=" + author + ", year=" + year + ", isbn=" + isbn
				+ ", imagepath=" + imagepath + ", storagepath=" + storagepath + "]";
	}

}
