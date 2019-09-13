package com.elibrary.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book implements Serializable {

	private static final long serialVersionUID = -3951164586605678995L;
	@Column(name = "book_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@Column
	private String author;
	@Column
	private Integer year;
	@Column
	private String isbn;
	@Column(name = "image_path")
	private String imagepath;
	@Column(name = "storage_path")
	private String storagepath;

	public Book() {
		super();
	}

	public Book(Integer id, String name, String author, Integer year, String isbn, String imagepath,
			String storagepath) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.year = year;
		this.isbn = isbn;
		this.imagepath = imagepath;
		this.storagepath = storagepath;
	}

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
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", year=" + year + ", isbn=" + isbn
				+ ", imagepath=" + imagepath + ", storagepath=" + storagepath + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
