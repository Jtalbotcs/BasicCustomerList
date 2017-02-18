package com.talbot.customerList.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.talbot.customerList.core.entities.Customer;
import com.talbot.customerList.rest.mvc.CustomerController;
import com.talbot.customerList.rest.resources.CustomerResource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Link;

public class CustomerResourceAsm extends ResourceAssemblerSupport<Customer, CustomerResource>{

	public CustomerResourceAsm() {
		super(CustomerController.class, CustomerResource.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CustomerResource toResource(Customer customer) {
		CustomerResource res = new CustomerResource();
		res.setEmail(customer.getEmail());
		res.setFirstname(customer.getFirstName());
		res.setLastname(customer.getLastName());
		res.setTelephone(customer.getTelephone());
		Link link = linkTo(CustomerController.class).slash(customer.getId()).withSelfRel();
		res.add(link);
		return res;
	}

}
