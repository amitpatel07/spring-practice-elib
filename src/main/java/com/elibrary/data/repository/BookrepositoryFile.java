package com.elibrary.data.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.elibrary.data.model.Book;

@Repository(value="bookrepositoryFile")
public class BookrepositoryFile implements BookRepository {
	@Value("${filerepo.basepath}")
	private String basepath;

	private final static String repopath = "book";
	private final static String idFileName = "id.txt";

	@Override
	public Book getBookByID(Integer id) {
		String path = getBookFileLocation() + id;
		File bookFile = new File(path);
		Book book = getBookFromFile(bookFile);
		return book;
	}

	@Override
	public Boolean deleteBook(Integer id) {
		Boolean result = false;
		if (id != null) {
			String path = getBookFileLocation() + id;
			File bookFile = new File(path);
			if (bookFile.exists()) {
				bookFile.delete();
				result=true;
			}
		}
		return result;
	}

	@Override
	public Book addbook(Book book) {
		if (book != null) {
			Integer bookId = book.getId();
			if (bookId == null) {
				bookId = getNextBookId();
				book.setId(bookId);
			}

			File bookFile = new File(getBookFileLocation() + bookId);
			try {
				FileOutputStream fout = new FileOutputStream(bookFile);
				ObjectOutputStream oout = new ObjectOutputStream(fout);
				oout.writeObject(book);
				fout.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return book;
	}

	@Override
	public List<Book> getBooks() {
		List<Book> books = null;
		File bookLocation = new File(getBookFileLocation());
		if (bookLocation.exists()) {
			File[] allBooks = bookLocation.listFiles();
			if (allBooks != null && allBooks.length > 1) {
				books = new ArrayList<Book>();
				for (File bookFile : allBooks) {
					if (!bookFile.getName().contains("id")) {
						books.add(getBookFromFile(bookFile));
					}
				}
			}
		}

		return books;
	}

	private String getBookFileLocation() {
		return (basepath + File.separator + repopath + File.separator);
	}

	private Book getBookFromFile(File bookFile) {
		Book book = null;
		try {
			FileInputStream fis = new FileInputStream(bookFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			book = (Book) ois.readObject();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	private synchronized int getNextBookId() {
		Integer newId = 1;
		File idFile = new File(getBookFileLocation() + idFileName);
		try {
			if (!idFile.exists()) {
				File parentFile = idFile.getParentFile();
				parentFile.mkdirs();
				idFile.createNewFile();
			}
			List<String> lines = new ArrayList<String>();
			Stream<String> streams = Files.lines(idFile.toPath());
			streams.forEach(stream -> lines.add(stream));
			String currentId = null;
			if (lines != null && !lines.isEmpty()) {
				currentId = lines.get(0);
			} else {
				currentId = "0";
			}
			streams.close();
			Integer currentIdInt = Integer.parseInt(currentId);
			newId = currentIdInt + 1;
			//
			Files.writeString(idFile.toPath(), newId.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newId;
	}

	public static void main(String[] args) {
		System.out.println(new BookrepositoryFile().getNextBookId());
	}
}
