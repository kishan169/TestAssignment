package com.project.orderservice.service;

import com.project.orderservice.dto.CustomerDto;
import com.project.orderservice.module.Customer;

public interface CustomerService {

    public Customer savedCustomer(CustomerDto customer);

    public Customer findCustomerByiD(Integer customerID);
}
