package com.elibrary.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Aspect
@Component
public class BookAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookAspect.class);

	@Pointcut("execution (* com.elibrary.service.BookService.updateBookImg(..)) ")
	public void addBookImageJoinpoint() {
	}

	@Before(value = "addBookImageJoinpoint() && args(image, bookId)")
	public void beforeBookImageAdvice(MultipartFile image, Integer bookId) {
		LOGGER.warn("Before File: " + image.getOriginalFilename());
		LOGGER.warn("Before BookId : " + bookId);
	}

	@After(value = "addBookImageJoinpoint() && args(image, bookId)")
	public void afterBookImageAdvice(MultipartFile image, Integer bookId) {
		LOGGER.warn("After File: " + image.getOriginalFilename());
		LOGGER.warn("AfterBookId : " + bookId);
	}
}
