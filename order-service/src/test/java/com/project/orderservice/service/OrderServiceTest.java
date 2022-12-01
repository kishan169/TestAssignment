package com.project.orderservice.service;
import static org.mockito.Mockito.*;
import com.project.orderservice.repository.CustomerDao;
import com.project.orderservice.repository.OrdersDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Mock
    private CustomerDao customerDao;

    @Mock
    private OrdersDao ordersDao;

    @BeforeEach
    void setUp(){
        this.orderService = new OrderServiceImpl(this.ordersDao,this.customerDao);
    }

    @Test
    void getAllOrdersById() {
        orderService.getAllOrdersById(1);
        verify(ordersDao).findByCustomerId(1);
    }


}