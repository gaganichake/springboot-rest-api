package com.johndeere.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.johndeere.repository.CustomerRepository;
import com.johndeere.resource.Customer;

@RestController
@RequestMapping(path="/customer") //This is optional.
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping("/add")
	public @ResponseBody String addNewCustomer(@RequestParam String firstName, @RequestParam String lastName) {
		
		Customer customer = new Customer(firstName, lastName);
		
		customerRepository.save(customer);
		
		return "SavedSuccessfully";
	}
	
	@GetMapping("/getAll")
	  public @ResponseBody Iterable<Customer> getAllUsers() {
	    // This returns a JSON or XML with the users
	    return customerRepository.findAll();
	  }
	
	@PutMapping("replace")
	public @ResponseBody String replaceCustomer(@RequestParam Customer customer) {

		customerRepository.save(customer);
		
		return "ReplacedSuccessfully";
	}
	
	@PatchMapping("update")
	public @ResponseBody String updateCustomer(@RequestParam Customer customerChanges) {
		
		
		Optional<Customer> customerOptional =  customerRepository.findById(customerChanges.getId());
		
		if(customerOptional.isPresent()) {
			
			Customer customer = customerOptional.get();
			if(customerChanges.getFirstName() != null) customer.setFirstName(customerChanges.getFirstName());
			if(customerChanges.getLastName() != null) customer.setLastName(customerChanges.getLastName());
			
			customerRepository.save(customerChanges);
		}
		
		return "ReplacedSuccessfully";
	}
	
}
