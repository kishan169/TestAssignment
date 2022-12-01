package com.project.orderservice.service;

import com.project.orderservice.dto.OrderDto;
import com.project.orderservice.exception.CustomerNotFoundException;
import com.project.orderservice.module.Orders;

import java.util.List;

public interface OrderService {

    public Orders savedOrders(OrderDto order, Integer customerId) throws CustomerNotFoundException;

    public List<Orders> getAllOrdersById(Integer customerId);
}
