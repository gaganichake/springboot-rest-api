package com.johndeere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.johndeere.repository.PageRepository;
import com.johndeere.resource.Page;

@RestController
@RequestMapping(path = "/page")
public class PageController {

	@Autowired
	private PageRepository pageRepository;
	
	
	@RequestMapping(path = "getAll", method = RequestMethod.GET)
	//@GetMapping("/getAll") // Shortcut
	public @ResponseBody Iterable<Page> getAllPages() {
		
		return pageRepository.findAll();
	}
}
