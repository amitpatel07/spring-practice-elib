package com.elibrary.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.elibrary.data.model.Book;
import com.elibrary.data.view.BookView;
import com.elibrary.data.view.Response;
import com.elibrary.service.BookService;

@Controller
public class BookController {
	@Autowired
	private BookService bookservice;

	@Autowired
	private HttpServletRequest request;

	@GetMapping(path = "/books")
	public String getBooks() {
		List<Book> books = bookservice.getBooks();
		request.setAttribute("books", books);
		return "book";
	}

	@RequestMapping(path = "/getBook", method = RequestMethod.GET, produces = { MimeTypeUtils.APPLICATION_JSON_VALUE,
			MimeTypeUtils.APPLICATION_XML_VALUE })
	@ResponseBody
	public Book getBookByID(@RequestParam(name = "id", required = true) Integer id) {
		return bookservice.getBookByID(id);

	}

	@PostMapping(path = "/addBook")
	public String addBook(@ModelAttribute BookView bookview) {
		bookservice.addBook(bookview);
		return getBooks();
	}

	@RequestMapping(path = "/deletebook", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, String> deleteBook(@RequestParam Integer id) {
		return bookservice.delete(id);

	}

	@PostMapping(path = "/uploadBookPdf", consumes = "multipart/form-data")
	public @ResponseBody Response uploadBookPdf(@RequestPart(name = "file") MultipartFile file,
			@RequestParam Integer bookId) {
		return bookservice.updateBookPdf(file, bookId);
	}
	
	@PostMapping(path = "/uploadBookImg", consumes = "multipart/form-data")
	public @ResponseBody Response uploadBookImg(@RequestPart(name = "image") MultipartFile image,
			@RequestParam Integer bookId) {
		return bookservice.updateBookImg(image, bookId);
	}
}
