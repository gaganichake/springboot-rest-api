package com.johndeere.test;

import com.examples.controller.GreetingController;
import com.examples.service.GreetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Spring Boot instantiates the whole web layer using @WebMvcTest
//In an application with multiple controllers, you can even ask for only one controller to be
//instantiated by using, for example, @WebMvcTest(HomeController.class)
@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private GreetingService service;
	
	@Test
	public void greetingShouldReturnMessageFromService() throws Exception {
		
		when(service.greet()).thenReturn("Hello, World!");
		
		this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Hello, World!")));
	}
	//We use @MockBean to create and inject a mock for the GreetingService (if you do not do so, 
	// the application context cannot start), and we set its expectations using Mockito
}
