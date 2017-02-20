package com.talbot.customerList.core.services;

import java.util.List;

import com.talbot.customerList.core.entities.Customer;
import com.talbot.customerList.core.services.util.CustomerList;

public interface CustomerService {
	/**
	 * @param id the id of the Customer to find
	 * @return the Customer or null if the Customer is not found
	 */
	public Customer findCustomer(Long id);
	
	/**
	 * @return all Customers in the database
	 */
	public CustomerList findAllCustomers();
	/**
	 * @param id the id of the Customer to delete
	 * @return the deleted Customer or null if not found
	 */
	public Customer deleteCustomer(Long id);
	/**
	 * @param id the id of the Customer to update
	 * @param data the up to date customer data
	 * @return the updated Customer or null if not found
	 */
	public Customer updateCustomer(Long id, Customer data);
	/**
	 * @param id the id of the customer to be added
	 * @param data the customer data to add to the database
	 * @return the new customer with a generated id
	 */
	public Customer addCustomer(Long id, Customer data);
}
