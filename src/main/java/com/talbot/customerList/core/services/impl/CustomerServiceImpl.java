package com.talbot.customerList.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talbot.customerList.core.entities.Customer;
import com.talbot.customerList.core.repositories.CustomerRepo;
import com.talbot.customerList.core.services.CustomerService;
import com.talbot.customerList.core.services.exceptions.CustomerEmailExistsException;
import com.talbot.customerList.core.services.exceptions.CustomerNotFoundException;
import com.talbot.customerList.core.services.exceptions.CustomerTelephoneExistsException;
import com.talbot.customerList.core.services.util.CustomerList;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public Customer findById(String id) {
		return customerRepo.findOne(id);
	}

	@Override
	public CustomerList findAllCustomers() {
		return new CustomerList(customerRepo.findAll());
	}

	@Override
	public Customer deleteCustomer(String id) {
		Customer customer = customerRepo.findOne(id);
		if (customer == null)
		{
			throw new CustomerNotFoundException();
		}
		customerRepo.delete(customer);
		return customer;
	}

	@Override
	public Customer updateCustomer(String id, Customer data) {
		Customer customer = customerRepo.findOne(id);
		if (customer == null)
		{
			throw new CustomerNotFoundException();
		}
		data.setId(id);
		return customerRepo.save(data);
	}

	@Override
	public Customer addCustomer(Customer data) {
        Customer customer = customerRepo.findCustomerByEmail(data.getEmail());
        if(customer != null)
        {
            throw new CustomerEmailExistsException();
        }
        customer = customerRepo.findCustomerByEmail(data.getTelephone());
        if(customer != null)
        {
        	throw new CustomerTelephoneExistsException();
        }
        return customerRepo.insert(data);
	}
}
