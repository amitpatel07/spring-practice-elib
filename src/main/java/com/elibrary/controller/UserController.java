package com.elibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elibrary.data.view.Response;
import com.elibrary.data.view.UserView;
import com.elibrary.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userservice;
	
	
	@PostMapping(path="/registerUser")
	public @ResponseBody Response registerUser(@RequestBody UserView userview) {
		
		return userservice.registerUser(userview);
		
	
	}
	@GetMapping(path="/getUser",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public @ResponseBody UserView getUser(@RequestParam String userName) {
		return userservice.getUser(userName);
		
	}
	
	
	

}
