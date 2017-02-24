package com.talbot.customerList.rest.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talbot.customerList.core.entities.Customer;
import com.talbot.customerList.core.services.exceptions.CustomerNotFoundException;
import com.talbot.customerList.core.services.CustomerService;
import com.talbot.customerList.core.services.util.CustomerList;
import com.talbot.customerList.rest.exceptions.NotFoundException;
import com.talbot.customerList.rest.resources.CustomerListResource;
import com.talbot.customerList.rest.resources.CustomerResource;
import com.talbot.customerList.rest.resources.asm.CustomerListResourceAsm;
import com.talbot.customerList.rest.resources.asm.CustomerResourceAsm;

@Controller
@RequestMapping("/rest/customer")
public class CustomerController {
	private CustomerService service;
	
	public CustomerController(CustomerService service){
		this.service = service;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<CustomerListResource> getAllCustomers()
	{
        CustomerList customerList = service.findAllCustomers();
        CustomerListResource customerListResource = new CustomerListResourceAsm().toResource(customerList);
        return new ResponseEntity<CustomerListResource>(customerListResource, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<CustomerResource> getCustomerById(
			@PathVariable String customerId)
	{
		try{
			Customer customer = service.findById(customerId);
			CustomerResource res = new CustomerResourceAsm().toResource(customer);
			return new ResponseEntity<CustomerResource>(res, HttpStatus.OK);
		} catch(CustomerNotFoundException e){
			throw new NotFoundException(e);
		}
	}
	@RequestMapping(value="/email/{email}", method = RequestMethod.GET)
	public ResponseEntity<CustomerResource> getCustomerByEmail(
			@PathVariable String email)
	{
		email = email.replace("_", "@").replace("-", ".");
		try{
			Customer customer = service.findByEmail(email);
			CustomerResource res = new CustomerResourceAsm().toResource(customer);
			return new ResponseEntity<CustomerResource>(res, HttpStatus.OK);
		} catch(CustomerNotFoundException e){
			throw new NotFoundException(e);
		}
	}
	@RequestMapping(value="/firstName/{firstName}", method = RequestMethod.GET)
	public ResponseEntity<CustomerListResource> getCustomerByFirstName(
			@PathVariable String firstName)
	{
		try{
			CustomerList customers = service.findByFirstName(firstName);
			CustomerListResource res = new CustomerListResourceAsm().toResource(customers);
			return new ResponseEntity<CustomerListResource>(res, HttpStatus.OK);
		} catch(CustomerNotFoundException e){
			throw new NotFoundException(e);
		}
	}
	@RequestMapping(value="/lastName/{lastName}", method = RequestMethod.GET)
	public ResponseEntity<CustomerListResource> getCustomerByLastName(
			@PathVariable String lastName)
	{
		try{
			CustomerList customers = service.findByLastName(lastName);
			CustomerListResource res = new CustomerListResourceAsm().toResource(customers);
			return new ResponseEntity<CustomerListResource>(res, HttpStatus.OK);
		} catch(CustomerNotFoundException e){
			throw new NotFoundException(e);
		}
	}
	@RequestMapping(value="/{customerId}", method = RequestMethod.DELETE)
	public ResponseEntity<CustomerResource> deleteCustomer(
			@PathVariable String customerId) {
		Customer entry = service.deleteCustomer(customerId);
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
			@PathVariable String customerId, @RequestBody CustomerResource sentCustomer) {
		Customer updatedEntry = service.updateCustomer(customerId, sentCustomer.toCustomer());
		if(updatedEntry != null)
		{
			CustomerResource res = new CustomerResourceAsm().toResource(updatedEntry);
			return new ResponseEntity<CustomerResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomerResource>(HttpStatus.NOT_FOUND);
		}
	}
}