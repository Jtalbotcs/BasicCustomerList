package com.talbot.customerList.rest.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talbot.customerList.core.entities.Customer;
import com.talbot.customerList.core.services.CustomerService;
import com.talbot.customerList.rest.resources.CustomerResource;
import com.talbot.customerList.rest.resources.asm.CustomerResourceAsm;
@Controller
@RequestMapping("/rest/customers")
public class CustomerController {
	private CustomerService service;
	
	public CustomerController(CustomerService service){
		this.service = service;
	}
	
	@RequestMapping(value="/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<CustomerResource> getCustomer(
			@PathVariable Long customerId)
	{
		Customer customer = service.find(customerId);
		if (customer != null) {
			CustomerResource res = new CustomerResourceAsm().toResource(customer);
			return new ResponseEntity<CustomerResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomerResource>(HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(value="/{customerId}", method = RequestMethod.DELETE)
	public ResponseEntity<CustomerResource> deleteCustomer(
			@PathVariable Long customerId) {
		Customer entry = service.delete(customerId);
		if(entry != null)
		{
			CustomerResource res = new CustomerResourceAsm().toResource(entry);
			return new ResponseEntity<CustomerResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomerResource>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value="/{customerId}", method = RequestMethod.PUT)
	public ResponseEntity<CustomerResource> updateCustomer(
			@PathVariable Long customerId, @RequestBody CustomerResource sentCustomer) {
		Customer updatedEntry = service.update(customerId, sentCustomer.toCustomer());
		if(updatedEntry != null)
		{
			CustomerResource res = new CustomerResourceAsm().toResource(updatedEntry);
			return new ResponseEntity<CustomerResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomerResource>(HttpStatus.NOT_FOUND);
		}
	}
}