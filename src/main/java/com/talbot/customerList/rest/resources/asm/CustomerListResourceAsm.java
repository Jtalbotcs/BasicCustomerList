package com.talbot.customerList.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.talbot.customerList.core.services.util.CustomerList;
import com.talbot.customerList.rest.mvc.CustomerController;
import com.talbot.customerList.rest.resources.CustomerListResource;

public class CustomerListResourceAsm extends ResourceAssemblerSupport<CustomerList, CustomerListResource>{
	public CustomerListResourceAsm()
    {
        super(CustomerController.class, CustomerListResource.class);
    }

    @Override
    public CustomerListResource toResource(CustomerList customerList) {
    	CustomerListResource res = new CustomerListResource();
        res.setCustomers(new CustomerResourceAsm().toResources(customerList.getCustomers()));
        return res;
    }
}
