package com.examples.controller;

import com.examples.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	@Autowired // Field injection
	private GreetingService service;

	@RequestMapping("/greeting")
	public @ResponseBody String greeting() {
		return service.greet();
	}
	
}
