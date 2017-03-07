package com.talbot.customerList.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import com.talbot.customerList.core.entities.Address;
import com.talbot.customerList.core.entities.Customer;

public class CustomerResource extends ResourceSupport {
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private Address address;


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	public Address getAddress() {
		return address;
	}
    public Customer toCustomer() {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setTelephone(telephone);
        customer.setAddress(address);
        return customer;
    }
}
