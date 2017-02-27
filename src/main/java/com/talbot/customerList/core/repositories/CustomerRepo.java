package com.talbot.customerList.core.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.talbot.customerList.core.entities.Customer;
/*
 * Interface for querying data from the customer collection.
 * 
 * This interface implements itself. 
 * 
 * Helpful resource http://www.baeldung.com/queries-in-spring-data-mongodb
 */
public interface CustomerRepo extends MongoRepository<Customer, String>{
	public Customer findCustomerByEmail(String email);
	public Customer findCustomerByTelephone(String telephone);
}
