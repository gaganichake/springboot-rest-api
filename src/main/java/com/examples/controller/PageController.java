package com.examples.controller;

import com.examples.repository.PageRepository;
import com.examples.resource.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/page")
public class PageController {

	@Autowired // Field injection
	private PageRepository pageRepository;

	@GetMapping("/getAll") // Shortcut
	public @ResponseBody Iterable<Page> getAllPages() {
		
		return pageRepository.findAll();
	}
}
