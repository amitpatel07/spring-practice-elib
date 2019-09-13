package com.elibrary.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.elibrary.data.model.Book;
import com.elibrary.data.repository.BookRepository;
import com.elibrary.data.view.BookView;
import com.elibrary.data.view.Response;

@Service
public class BookService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);
	@Autowired
	@Qualifier(value = "bookRepositoryDb")
	private BookRepository bookrepository;

	public Book getBookByID(Integer id) {
		Book book = null;
		if (id != null) {
			book = bookrepository.getBookByID(id);
		}
		return book;

	}

	@Transactional
	public Map<String, String> delete(Integer id) {
		boolean result = bookrepository.deleteBook(id);
		Map<String, String> map = new HashMap<String, String>();
		if (result) {
			map.put("result", "deleted successfully");

		} else {
			map.put("result", "can Not delete");
		}
		return map;
	}

	public List<Book> getBooks() {
		LOGGER.debug("inside get book");
		return bookrepository.getBooks();
	}

	@Transactional
	public Book addBook(BookView bookview) {
		LOGGER.debug("inside add book");
		Book book = null;
		try {
			if (bookview != null) {
				book = new Book();
				book.setAuthor(bookview.getAuthor());
				book.setImagepath(bookview.getImagepath());
				book.setIsbn(bookview.getIsbn());
				book.setName(bookview.getName());
				book.setStoragepath(bookview.getStoragepath());
				book.setYear(bookview.getYear());
				bookrepository.addbook(book);
			}
		} catch (Exception e) {
			LOGGER.error("error during add book : ", e);
		}
		LOGGER.debug("before return from addbook");
		return book;
	}

	@Transactional
	public Response updateBookPdf(MultipartFile file, Integer bookId) {
		String message = null;
		String status = "failure";

		Book book = bookrepository.getBookByID(bookId);
		if (book != null) {
			// 1. Create file path - needs to be unique
			String path = "/opt/uploadedFiles/" + System.currentTimeMillis() + "__" + file.getOriginalFilename();
			File dest = new File(path);
			try {
				file.transferTo(dest);
				book.setStoragepath(path);
				bookrepository.addbook(book);
				status = "success";
				message = "book updated";
			} catch (IllegalStateException | IOException e) {
				message = "Error saving file : " + e.getMessage();
				LOGGER.error(message, e);
			}
		}

		return new Response(message, status);

	}

}
