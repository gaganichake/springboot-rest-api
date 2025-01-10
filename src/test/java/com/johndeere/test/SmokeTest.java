package com.johndeere.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.johndeere.controller.UserController;

@SpringBootTest
class SmokeTest {

	@Autowired
	private UserController controller;
	
	@Test
	void contextLoads() throws Exception{
		assertNotNull(controller);
	}

}
