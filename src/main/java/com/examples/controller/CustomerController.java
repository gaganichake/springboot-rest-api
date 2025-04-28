package com.examples.controller;

import com.examples.repository.CustomerRepository;
import com.examples.resource.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
