package com.project.orderservice.repository;

import com.project.orderservice.module.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDao extends JpaRepository<Orders,Integer> {

    public List<Orders> findByCustomerId(Integer customerId);
}
