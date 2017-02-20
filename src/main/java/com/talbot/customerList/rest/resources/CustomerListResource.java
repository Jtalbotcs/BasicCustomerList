package com.talbot.customerList.rest.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class CustomerListResource extends ResourceSupport{
	private List<CustomerResource> customers = new ArrayList<CustomerResource>();
	
    public List<CustomerResource> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerResource> customers) {
        this.customers = customers;
    }
}
