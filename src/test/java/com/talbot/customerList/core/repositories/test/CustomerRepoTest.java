package com.talbot.customerList.core.repositories.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.talbot.customerList.core.entities.Address;
import com.talbot.customerList.core.entities.Customer;
import com.talbot.customerList.core.repositories.CustomerRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:mongodb-config.xml")
public class CustomerRepoTest {
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Before
	public void init(){
		customerRepo.deleteAll();
		Customer customerA = new Customer();
		customerA.setFirstName("Bob");
		customerA.setLastName("Ross");
		customerA.setEmail("fwef@gmail.com");
		customerA.setTelephone("4014354833");
		Address customerAddressA = new Address();
		customerAddressA.setStreet("5 Leigh Rd");
		customerAddressA.setCity("Riverside");
		customerAddressA.setState("Rhode Island");
		customerAddressA.setZip("02915");
		customerA.setAddress(customerAddressA);

		Customer customerB = new Customer();
		customerB.setFirstName("Sam");
		customerB.setLastName("Fisher");
		customerB.setEmail("sfisher@gmail.com");
		customerB.setTelephone("4014452343");
		Address customerAddressB = new Address();
		customerAddressB.setStreet("54 Tower Ave");
		customerAddressB.setCity("Cumberland");
		customerAddressB.setState("Maine");
		customerAddressB.setZip("04021");
		customerB.setAddress(customerAddressB);

		customerRepo.save(customerA);
		customerRepo.save(customerB);
	}
	@Test
	public void findAllCustomers()
	{
		List<Customer> customers = customerRepo.findAll();
		assertEquals(2, customers.size());
	}
}
