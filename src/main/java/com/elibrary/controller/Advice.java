package com.elibrary.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elibrary.data.view.Response;

@ControllerAdvice
public class Advice {

	@Autowired
	private HttpServletResponse servletResponse;

	@ExceptionHandler(value = SizeLimitExceededException.class)
	public @ResponseBody Response FileLimitAdvice(SizeLimitExceededException e) {
		String message = e.getMessage();
		String status = "Failure Due to File Size";
		servletResponse.setStatus(413);
		return new Response(message, status);
	}

}
