package com.johndeere.repository.rest;

import com.johndeere.resource.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

// Directory exposing the repository as Rest API using Spring Data REST
@RepositoryRestResource(collectionResourceRel = "customer", path = "restCustomer")
public interface CustomerRestRepository extends PagingAndSortingRepository<Customer, Long> {

	public List<Customer> findByFirstName(@Param("firstName") String firstName);
	
}
