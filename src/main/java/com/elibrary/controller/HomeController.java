package com.elibrary.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	// Separation of Concerns

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	@Value("${app.name}")
	private String appName;

	@GetMapping(path = { "/heartbeat" })
	public @ResponseBody Object heartbeat() {
		Map<String, String> map = new HashMap<>();
		map.put("App", appName);
		map.put("Status", "running");
		return map;
	}

	@GetMapping(path = { "/", "/home" })
	public String home() {
		for (int i = 0; i < 99; i++) {
			LOGGER.info("simple logging :" + i);
		}
		return "home";
	}

}
