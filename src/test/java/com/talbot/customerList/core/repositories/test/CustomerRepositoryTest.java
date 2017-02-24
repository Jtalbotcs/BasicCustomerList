package com.talbot.customerList.core.repositories.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.talbot.customerList.core.entities.Customer;
import com.talbot.customerList.core.repositories.CustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:mongodb-config.xml")
public class CustomerRepositoryTest {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Before
	public void init(){
		customerRepository.deleteAll();
		Customer customerA = new Customer();
		customerA.setFirstName("Bob");
		customerA.setLastName("Ross");
		customerA.setEmail("fwef@gmail.com");
		customerA.setTelephone("4014354833");

		Customer customerB = new Customer();
		customerB.setFirstName("Sam");
		customerB.setLastName("Fisher");
		customerB.setEmail("sfisher@gmail.com");
		customerB.setTelephone("4014452343");

		customerRepository.save(customerA);
		customerRepository.save(customerB);
	}
	@Test
	public void findAll()
	{
		List<Customer> customers = customerRepository.findAll();
		assertEquals(2, customers.size());
	}
}
