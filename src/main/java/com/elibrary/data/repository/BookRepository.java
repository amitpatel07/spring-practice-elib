package com.elibrary.data.repository;

import java.util.List;

import com.elibrary.data.model.Book;

public interface BookRepository {
	
	Book getBookByID (Integer id);

	Book addbook(Book book);

	List<Book> getBooks();

	Boolean deleteBook(Integer id);
}
