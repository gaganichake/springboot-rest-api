package com.examples.controller;

import com.examples.model.Customer;
import com.examples.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/customer") //This is optional.
public class CustomerController {

	private CustomerRepository customerRepository;

	@Autowired // Setter injection
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@PostMapping("/add")
	public @ResponseBody String addNewCustomer(@RequestParam String firstName, @RequestParam String lastName) {

		com.examples.resource.Customer customer = new com.examples.resource.Customer(firstName, lastName);
		
		customerRepository.save(customer);
		
		return "Saved Successfully";
	}
	
	@GetMapping("/getAll")
	  public @ResponseBody Iterable<com.examples.resource.Customer> getAllUsers() {
	    // This returns a JSON or XML with the users
	    return customerRepository.findAll();
	  }
	
	@PutMapping("replace")
	public @ResponseBody String replaceCustomer(@RequestBody Customer customer) {

		Optional<com.examples.resource.Customer> customerOptional =  customerRepository.findById(customer.getId());

		if(customerOptional.isPresent()) {
			customerRepository.save(customer.replace(customerOptional.get()));
			return "Replaced Successfully";
		} else {
			return "Customer not found";
		}
	}
	
	@PatchMapping("update")
	public @ResponseBody String updateCustomer(@RequestBody Customer customer) {

		Optional<com.examples.resource.Customer> customerOptional =  customerRepository.findById(customer.getId());
		
		if(customerOptional.isPresent()) {
			customerRepository.save(customer.update(customerOptional.get()));
			return "Updated Successfully";
		} else {
			return "Customer not found";
		}
	}

}
