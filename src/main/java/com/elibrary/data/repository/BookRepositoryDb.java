package com.elibrary.data.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.elibrary.data.model.Book;

@Repository(value = "bookRepositoryDb")
public class BookRepositoryDb implements BookRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookRepositoryDb.class);

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	// JPQL - JPA Query Language / HQL - Hibernate Query Language
	
	@Override
	public Book getBookByID(Integer id) {
		Book book = null;
		try {
			String query = "select b from Book b where b.id= :inputId ";// to avoid sql injection
			Query jpaQuery = entityManager.createQuery(query);
			jpaQuery.setParameter("inputId", id); // named parameters
			book = (Book) jpaQuery.getSingleResult();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return book;
	}

	@Override
	public Book addbook(Book book) {
		if (book != null) {
			if (book.getId() != null) {
				// Update
				entityManager.merge(book);
			} else {
				entityManager.persist(book);
			}
		}
		return book;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getBooks() {
		List<Book> books = null;
		try {
			String query = "select b from Book b";
			Query jpaQuery = entityManager.createQuery(query);
			books = (List<Book>) jpaQuery.getResultList();

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return books;
	}

	@Override
	public Boolean deleteBook(Integer id) {
		Boolean deleted = false;
		try {
			String query = "delete from Book b where b.id= :inputId";
			Query jpaQuery = entityManager.createQuery(query);
			jpaQuery.setParameter("inputId", id);
			int i = jpaQuery.executeUpdate();
			if (i > 0) {
				deleted = true;
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return deleted;
	}

}
