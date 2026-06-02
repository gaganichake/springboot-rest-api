package com.examples.model;

import lombok.Data;

@Data
public class Customer {

    private Long Id;
    private String firstName;
    private String lastName;

    public com.examples.resource.Customer replace(com.examples.resource.Customer customer) {
        customer.setFirstName(this.firstName);
        customer.setLastName(this.lastName);
        return customer;
    }

    public com.examples.resource.Customer update(com.examples.resource.Customer customer) {
        if(this.firstName != null) customer.setFirstName(this.firstName);
        if(this.lastName != null) customer.setLastName(this.lastName);
        return customer;
    }
}
