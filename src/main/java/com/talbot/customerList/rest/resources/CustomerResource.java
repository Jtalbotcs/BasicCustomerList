package com.talbot.customerList.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import com.talbot.customerList.core.entities.Customer;

public class CustomerResource extends ResourceSupport {
	private String firstname;
	private String lastname;
	private String email;
	private String telephone;


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	
    public Customer toCustomer() {
        Customer customer = new Customer();
        customer.setFirstName(firstname);
        customer.setLastName(lastname);
        customer.setEmail(email);
        customer.setTelephone(telephone);
        return customer;
    }
}
