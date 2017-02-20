package com.talbot.customerList.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.talbot.customerList.core.entities.Customer;
/*
 * Interface for querying data from the customer collection.
 * 
 * This interface implements itself. 
 * 
 * There are a lot of ways to query the repository. I will be using QueryDSL for type safety, ease of use, and readability.
 * 
 * Helpful resource http://www.baeldung.com/queries-in-spring-data-mongodb
 */
public interface CustomerRepository extends MongoRepository<Customer, String>, QueryDslPredicateExecutor<Customer>{
}
