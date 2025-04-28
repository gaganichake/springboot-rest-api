package com.examples.resource;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity // This tells Hibernate to make a table out of this class. Default table name is Java class name.
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@Setter
    private String firstName;
	@Setter
    private String lastName;

	public Customer(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

    @Override
	public String toString() {
		return "Customer [Id=" + Id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
