package com.examples.controller;

import com.examples.resource.User;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	private static final String template = "Hello %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	
	@GetMapping("greet")
	public User greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		
		return new User(counter.incrementAndGet(), String.format(template, name));
	}
	
	@GetMapping("greet/{name}")
	public User greetingByName(@PathVariable("name") String name) {
		
		return new User(counter.incrementAndGet(), String.format(template, name));
	}
	
}
