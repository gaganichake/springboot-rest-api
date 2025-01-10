package com.johndeere.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.johndeere.resource.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

	public List<Customer> findByLastName(String lastName);
	
	public Customer findById(long id);
	
}
