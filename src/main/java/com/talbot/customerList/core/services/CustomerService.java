package com.talbot.customerList.core.services;

import com.talbot.customerList.core.entities.Customer;

public interface CustomerService {
	public Customer find(Long id);

	public Customer delete(Long id);

	public Customer update(Long id, Customer customer);
}
