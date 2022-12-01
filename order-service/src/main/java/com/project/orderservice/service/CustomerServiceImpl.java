package com.project.orderservice.service;

import com.project.orderservice.dto.CustomerDto;
import com.project.orderservice.module.Category;
import com.project.orderservice.module.Customer;
import com.project.orderservice.repository.CustomerDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerDao customerDao;

    @Override
    public Customer savedCustomer(CustomerDto dto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto,customer);
        customer.setCategory(Category.REGULAR);

        return customerDao.save(customer);
    }

    @Override
    public Customer findCustomerByiD(Integer customerID) {
        return customerDao.findById(customerID).get();
    }
}
