package com.examples.controller;

import com.examples.repository.BookRepository;
import com.examples.resource.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/book")
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	
	@RequestMapping(path = "getAll", method = RequestMethod.GET)
	//@GetMapping("/getAll") // Shortcut
	public @ResponseBody Iterable<Book> getAllBooks() {
		
		return bookRepository.findAll();
	}
}
