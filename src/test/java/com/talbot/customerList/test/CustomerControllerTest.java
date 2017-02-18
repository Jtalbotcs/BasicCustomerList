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
import com.talbot.customerList.core.services.CustomerService;
import com.talbot.customerList.rest.mvc.CustomerController;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {
	@InjectMocks
	private CustomerController controller;
	private final String testJSONCustomer = "{\"firstname\":\"Bob\",\"lastname\":\"Ross\",\"email\":\"fwef@gmail.com\",\"telephone\":\"4014354833\"}";
	
	private MockMvc mockMvc;
	
	@Mock
	private CustomerService service;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void getExistingCustomer() throws Exception{
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setFirstName("Bob");
		customer.setLastName("Ross");
		customer.setEmail("fwef@gmail.com");
		customer.setTelephone("4014354833");
		
		when(service.find(1L)).thenReturn(customer);
		
        mockMvc.perform(get("/rest/customers/1"))
        .andDo(print())
        .andExpect(jsonPath("$.firstname", is(customer.getFirstName())))
        .andExpect(jsonPath("$.lastname", is(customer.getLastName())))
        .andExpect(jsonPath("$.email", is(customer.getEmail())))
        .andExpect(jsonPath("$.telephone", is(customer.getTelephone())))
        .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/customers/1"))))
        .andExpect(status().isOk());
	}
	
	@Test
	public void getNonExistingCustomer() throws Exception{
		when(service.find(1L)).thenReturn(null);
		
        mockMvc.perform(get("/rest/customers/1"))
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

        when(service.delete(1L)).thenReturn(deletedCustomer);

        mockMvc.perform(delete("/rest/customers/1"))
        	.andExpect(jsonPath("$.firstname", is(deletedCustomer.getFirstName())))
        	.andExpect(jsonPath("$.lastname", is(deletedCustomer.getLastName())))
        	.andExpect(jsonPath("$.email", is(deletedCustomer.getEmail())))
        	.andExpect(jsonPath("$.telephone", is(deletedCustomer.getTelephone())))
        	.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/customers/1"))))
        	.andExpect(status().isOk());
    }

    @Test
    public void deleteNonExistingCustomer() throws Exception {
        when(service.delete(1L)).thenReturn(null);

        mockMvc.perform(delete("/rest/customers/1"))
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

        when(service.update(eq(1L), any(Customer.class)))
                .thenReturn(updatedEntry);

        mockMvc.perform(put("/rest/customers/1")
                .content(testJSONCustomer)
                .contentType(MediaType.APPLICATION_JSON))
    			.andExpect(jsonPath("$.firstname", is(updatedEntry.getFirstName())))
    			.andExpect(jsonPath("$.lastname", is(updatedEntry.getLastName())))
    			.andExpect(jsonPath("$.email", is(updatedEntry.getEmail())))
    			.andExpect(jsonPath("$.telephone", is(updatedEntry.getTelephone())))
    			.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/customers/1"))))
    			.andExpect(status().isOk());
    }

    @Test
    public void updateNonExistingCustomer() throws Exception {
        when(service.update(eq(1L), any(Customer.class)))
                .thenReturn(null);

        mockMvc.perform(put("/rest/customers/1")
                .content(testJSONCustomer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

