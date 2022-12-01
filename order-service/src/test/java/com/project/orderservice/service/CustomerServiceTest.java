package com.project.orderservice.service;

import com.project.orderservice.dto.CustomerDto;
import com.project.orderservice.module.Category;
import com.project.orderservice.module.Customer;
import com.project.orderservice.repository.CustomerDao;
import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerService customerService;


    @Test
    void findById(){
        Customer customer = new Customer(5,"Kishan","8200428153","Kishan7852", Category.REGULAR);
        assertThat(customerDao.findById(5).get()).isEqualTo(customer);
    }

    @Test
    void savedCustomer(){
        Customer customer = new Customer(7,"Kishan","8200428153","Kishan7852", Category.REGULAR);

        CustomerDto dto = new CustomerDto(7,"Kishan","8200428153","Kishan7852");
        assertThat(customerService.savedCustomer(dto)).isEqualTo(customer);
    }

}