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

	private final BookRepository bookRepository;

	// Use constructor injection if you are not using @Autowired.
	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@RequestMapping(path = "getAll", method = RequestMethod.GET)
	public @ResponseBody Iterable<Book> getAllBooks() {
		return bookRepository.findAll();
	}
}
