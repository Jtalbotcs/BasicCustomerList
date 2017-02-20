package com.talbot.customerList.core.services.util;

import com.talbot.customerList.core.entities.Customer;

import java.util.ArrayList;
import java.util.List;


public class CustomerList {

    private List<Customer> customers = new ArrayList<Customer>();

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}