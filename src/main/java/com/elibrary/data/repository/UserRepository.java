package com.elibrary.data.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.elibrary.data.model.User;

@Repository
public class UserRepository {
	private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	public User addUser(User user) {
		if (user != null) {
			if (user.getId() != null)
				entityManager.merge(user);
			else
				entityManager.persist(user);
		}
		return user;
	}

	public User getUser(Integer userId) {
		User user = null;
		try {
			String query = "select u from User u where u.id= :userId";
			Query jpaQuery = entityManager.createQuery(query);
			jpaQuery.setParameter("userId", userId);
			user = (User) jpaQuery.getSingleResult();
		} catch (Exception e) {
			logger.error("getting user error", e);
		}
		return user;
	}

	public User getUser(String userName) {
		User user = null;
		try {
			String query = "select u from User u where u.username= :username";
			Query jpaQuery = entityManager.createQuery(query);
			jpaQuery.setParameter("username", userName);
			user = (User) jpaQuery.getSingleResult();
		} catch (Exception e) {
			logger.error("User name error", e);
		}
		return user;
	}

	public boolean deleteUSer(Integer userId) {
		Boolean deleted = false;
		try {

			String query = "delete from User u where u.id= :inputId";
			Query jpaQuery = entityManager.createQuery(query);
			jpaQuery.setParameter("inputId", userId);
			int i = jpaQuery.executeUpdate();
			if (i > 0) {
				deleted = true;
			}
		} catch (Exception e) {
			logger.error("error deleting user");
		}
		return deleted;

	}
}
