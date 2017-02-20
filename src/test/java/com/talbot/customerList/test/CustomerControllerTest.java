package com.talbot.customerList.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.talbot.customerList.core.entities.Customer;
import com.talbot.customerList.core.services.exceptions.CustomerNotFoundException;
import com.talbot.customerList.core.services.CustomerService;
import com.talbot.customerList.core.services.util.CustomerList;
import com.talbot.customerList.rest.mvc.CustomerController;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

public class CustomerControllerTest {
	@InjectMocks
	private CustomerController controller;
	private final String testJSONCustomer = "{\"firstName\":\"Bob\",\"lastName\":\"Ross\",\"email\":\"fwef@gmail.com\",\"telephone\":\"4014354833\"}";
	
	private MockMvc mockMvc;
	
	@Mock
	private CustomerService service;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
    @Test
    public void findAllBlogs() throws Exception {
        List<Customer> list = new ArrayList<Customer>();

        Customer customerA = new Customer();
        customerA.setId(1L);
        customerA.setFirstName("Bob");
        customerA.setLastName("Ross");
		customerA.setEmail("fwef@gmail.com");
		customerA.setTelephone("4014354833");
        list.add(customerA);
        
        Customer customerB = new Customer();
        customerB.setId(1L);
        customerB.setFirstName("Sam");
        customerB.setLastName("Fisher");
        customerB.setEmail("sfisher@gmail.com");
        customerB.setTelephone("4014452343");
        list.add(customerB);

        CustomerList allCustomers = new CustomerList();
        allCustomers.setCustomers(list);

        when(service.findAllCustomers()).thenReturn(allCustomers);

        mockMvc.perform(get("/rest/customer")).andDo(print())
                .andExpect(jsonPath("$.customers[*].email",
                        hasItems(endsWith("fwef@gmail.com"), endsWith("sfisher@gmail.com"))))
                .andExpect(status().isOk());
    }

	@Test
	public void getExistingCustomer() throws Exception{
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setFirstName("Bob");
		customer.setLastName("Ross");
		customer.setEmail("fwef@gmail.com");
		customer.setTelephone("4014354833");
		
		when(service.findCustomer(1L)).thenReturn(customer);
		
        mockMvc.perform(get("/rest/customer/1"))
        .andExpect(jsonPath("$.firstName", is(customer.getFirstName())))
        .andExpect(jsonPath("$.lastName", is(customer.getLastName())))
        .andExpect(jsonPath("$.email", is(customer.getEmail())))
        .andExpect(jsonPath("$.telephone", is(customer.getTelephone())))
        .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/customer/1"))))
        .andExpect(status().isOk());
	}
	
	@Test
	public void getNonExistingCustomer() throws Exception{
		when(service.findCustomer(1L)).thenThrow(new CustomerNotFoundException("Can not find customer with id \"1L\""));
		
        mockMvc.perform(get("/rest/customer/1"))
        .andExpect(status().isNotFound());
	}
	
    @Test
    public void deleteExistingCustomer() throws Exception {
        Customer deletedCustomer = new Customer();
        deletedCustomer.setId(1L);
        deletedCustomer.setFirstName("Bob");
        deletedCustomer.setLastName("Ross");
        deletedCustomer.setEmail("fwef@gmail.com");
        deletedCustomer.setTelephone("4014354833");

        when(service.deleteCustomer(1L)).thenReturn(deletedCustomer);

        mockMvc.perform(delete("/rest/customer/1"))
        	.andExpect(jsonPath("$.firstName", is(deletedCustomer.getFirstName())))
        	.andExpect(jsonPath("$.lastName", is(deletedCustomer.getLastName())))
        	.andExpect(jsonPath("$.email", is(deletedCustomer.getEmail())))
        	.andExpect(jsonPath("$.telephone", is(deletedCustomer.getTelephone())))
        	.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/customer/1"))))
        	.andExpect(status().isOk());
    }

    @Test
    public void deleteNonExistingCustomer() throws Exception {
        when(service.deleteCustomer(1L)).thenReturn(null);

        mockMvc.perform(delete("/rest/customer/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateExistingCustomer() throws Exception {
        Customer updatedEntry = new Customer();
        updatedEntry.setId(1L);
        updatedEntry.setFirstName("Bob");
        updatedEntry.setLastName("Ross");
        updatedEntry.setEmail("fwef@gmail.com");
        updatedEntry.setTelephone("4014354833");

        when(service.updateCustomer(eq(1L), any(Customer.class)))
                .thenReturn(updatedEntry);

        mockMvc.perform(put("/rest/customer/1")
                .content(testJSONCustomer)
                .contentType(MediaType.APPLICATION_JSON))
    			.andExpect(jsonPath("$.firstName", is(updatedEntry.getFirstName())))
    			.andExpect(jsonPath("$.lastName", is(updatedEntry.getLastName())))
    			.andExpect(jsonPath("$.email", is(updatedEntry.getEmail())))
    			.andExpect(jsonPath("$.telephone", is(updatedEntry.getTelephone())))
    			.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/customer/1"))))
    			.andExpect(status().isOk());
    }

    @Test
    public void updateNonExistingCustomer() throws Exception {
        when(service.updateCustomer(eq(1L), any(Customer.class)))
                .thenReturn(null);

        mockMvc.perform(put("/rest/customer/1")
                .content(testJSONCustomer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

