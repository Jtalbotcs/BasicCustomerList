package com.talbot.customerList.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.talbot.customerList.model.Customer;

public interface CustomersRepository extends MongoRepository<Customer, String>{
	
	List<Customer> findByLastName(@Param("name") String name);	
	List<Customer> findByFirstName(@Param("name") String name);	

}
