package com.project.orderservice.controller;

import com.project.orderservice.dto.CustomerDto;
import com.project.orderservice.dto.OrderDto;
import com.project.orderservice.exception.CustomerNotFoundException;
import com.project.orderservice.module.Customer;
import com.project.orderservice.module.Orders;
import com.project.orderservice.service.CustomerService;
import com.project.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class OrderController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService ordersService;

    @PostMapping("/customers")
    public ResponseEntity<Customer> savedCustomer(@Valid @RequestBody CustomerDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.savedCustomer(dto));
    }

    @GetMapping("/customers/{id}")
    public  ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer customerId){
        Customer customer = customerService.findCustomerByiD(customerId);
        customer.setOrders(ordersService.getAllOrdersById(customerId));
        return ResponseEntity.status(HttpStatus.FOUND).body(customer);
    }

    @PostMapping("/customers/{id}/orders")
    private ResponseEntity<Orders> savedOrders(@Valid @RequestBody OrderDto dto, @PathVariable("id") Integer customerId) throws CustomerNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.savedOrders(dto,customerId));
    }

}
