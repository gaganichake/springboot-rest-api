package com.johndeere;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.johndeere.repository.BookRepository;
import com.johndeere.repository.CustomerRepository;
import com.johndeere.repository.PageRepository;
import com.johndeere.resource.Book;
import com.johndeere.resource.Customer;
import com.johndeere.resource.Page;

@SpringBootApplication
public class DemoApplication {

	private static final Logger log =  LoggerFactory.getLogger(DemoApplication.class);
	
	public static void main(String[] args) {
		
		SpringApplication.run(DemoApplication.class, args);
	
	}
	
//	@Bean
	public CommandLineRunner demo(CustomerRepository customerRepository, BookRepository bookRepository, PageRepository pageRepository) {
		
		return (arg) -> {
			// Save a few customers
			customerRepository.save(new Customer("Gagan", "Ichake"));
			customerRepository.save(new Customer("Garvit", "Ichake"));
			customerRepository.save(new Customer("Chloe", "O'Brian"));
			customerRepository.save(new Customer("Kim", "Bauer"));
			customerRepository.save(new Customer("David", "Palmer"));
			customerRepository.save(new Customer("Michelle", "Dessler"));
			
			// fetch all customers
			log.info("Customers found with findAll()");
			log.info("------------------------------");
			// Iterating using Java 6 feature
			for(Customer customer: customerRepository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");
			
			// fetch an individual customer by ID
			Customer customer = customerRepository.findById(1L);
			log.info("Customer found with findbyId()");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");
			
			// fetch customer by last name
			log.info("Customer found with findByLastName('Ichake')");
			log.info("--------------------------------------------");
			// Iterating using Java 8 feature
			customerRepository.findByLastName("Ichake").forEach(ichake -> {
				log.info(ichake.toString());
			});
		    // for (Customer bauer : repository.findByLastName("Bauer")) {
		    //  log.info(bauer.toString());
		    // 
			log.info("");
			
			log.info("OneToMany relationship");
			log.info("--------------------------------------------");
			log.info("Create a new book");
			Book book = new Book("John","Java Recipe");
			
			log.info("Save the book");
			bookRepository.save(book);
			
			log.info("Create and save new pages");
			pageRepository.save(new Page("One", "Introduction", 1, book));
			pageRepository.save(new Page("One", "Introduction", 2, book));
			pageRepository.save(new Page("Two", "Java 8 features", 3, book));
			pageRepository.save(new Page("Two", "Java 8 features", 4, book));
			pageRepository.save(new Page("Two", "Java 8 features", 5, book));
			pageRepository.save(new Page("Three", "Concurrency", 6, book));
			pageRepository.save(new Page("Three", "Concurrency", 5, book));
			log.info(book.toString());
			
		};
	}
}
