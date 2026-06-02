package com.examples;

import com.examples.repository.BookRepository;
import com.examples.repository.CustomerRepository;
import com.examples.repository.PageRepository;
import com.examples.resource.Book;
import com.examples.resource.Customer;
import com.examples.resource.Page;
import com.examples.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class DemoApplication {

	private static final Logger log =  LoggerFactory.getLogger(DemoApplication.class);
	
	public static void main(String[] args) {
		
		SpringApplication.run(DemoApplication.class, args);
	
	}
	
	@Bean
	public CommandLineRunner demo(CustomerRepository customerRepo, BookRepository bookRepo, PageRepository pageRepo, BookService bookService) {
		
		return (arg) -> {
			// Save a few customers
			customerRepo.save(new Customer("Gagan", "Ichake"));
			customerRepo.save(new Customer("Garvit", "Ichake"));
			customerRepo.save(new Customer("Chloe", "O'Brian"));
			customerRepo.save(new Customer("Kim", "Bauer"));
			customerRepo.save(new Customer("David", "Palmer"));
			customerRepo.save(new Customer("Michelle", "Dessler"));
			
			// fetch all customers
			log.info("Customers found with findAll()");
			log.info("------------------------------");

			Iterable<Customer> customers = customerRepo.findAll();
			// Iterating using Java 6 feature
			for(Customer customer: customers) {
				log.info(customer.toString());
			}
			log.info("");
			
			// fetch an individual customer by ID
			Customer customer = customerRepo.findById(1L);
			log.info("Customer found with findbyId()");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");
			
			// fetch customer by last name
			log.info("Customer found with findByLastName('Ichake')");
			log.info("--------------------------------------------");
			// Iterating using Java 8 feature
			customerRepo.findByLastName("Ichake").forEach(ichake -> log.info(ichake.toString()));

			log.info("");
			
			log.info("OneToMany relationship");
			log.info("--------------------------------------------");
			log.info("Create a new book");
			Book book = new Book("John","Java Recipe");
			log.info("Save the book");
			bookRepo.save(book);
            log.info("Book: {}", book);
			
			log.info("Create and save new pages");
			pageRepo.save(new Page("One", "Introduction", 1, book));
			pageRepo.save(new Page("One", "Introduction", 2, book));
			pageRepo.save(new Page("Two", "Java 8 features", 3, book));
			pageRepo.save(new Page("Two", "Java 8 features", 4, book));
			pageRepo.save(new Page("Two", "Java 8 features", 5, book));
			pageRepo.save(new Page("Three", "Concurrency", 6, book));
			pageRepo.save(new Page("Three", "Concurrency", 5, book));

			// fetch all pages
			log.info("Pages found with findAll()");
			log.info("------------------------------");
			pageRepo.findAll().forEach(page -> log.info(page.toString()));

			log.info("");

            log.info("book = {}", book);
//			book.getPages().forEach(page -> log.info(page.toString())); // java.lang.NullPointerException as pages are not loaded yet.

			Optional<Book> initializedBook = bookService.findByIdWithPages(book.getId()); // Now pages are loaded.
            log.info("initializedBook = {}", initializedBook);
            if (initializedBook.isPresent()) {
                initializedBook.get().getPages().forEach(page -> log.info(page.toString()));
            } else {
                log.info("Book not present");
            }
        };
	}
}
