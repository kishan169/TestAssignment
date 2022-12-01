package com.project.orderservice.service;

import com.project.orderservice.dto.OrderDto;
import com.project.orderservice.exception.CustomerNotFoundException;
import com.project.orderservice.module.Category;
import com.project.orderservice.module.Customer;
import com.project.orderservice.module.Orders;
import com.project.orderservice.repository.CustomerDao;
import com.project.orderservice.repository.OrdersDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    public OrderServiceImpl(OrdersDao ordersDao, CustomerDao customerDao) {
        this.ordersDao = ordersDao;
        this.customerDao = customerDao;
    }

    @Autowired
    private OrdersDao ordersDao;

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Orders savedOrders(OrderDto dto, Integer customerId) throws CustomerNotFoundException {

        // get customer
        Optional<Customer> optionalCustomer = customerDao.findById(customerId);

        if(!optionalCustomer.isPresent()) throw new CustomerNotFoundException("User is Not Found in Database");
        Customer customer = optionalCustomer.get();

        Orders savedOrder = null;

        // parse date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dto.getOrderDate(), formatter);

        // create order object
        Orders order = new Orders();
        order.setCustomerId(customerId);
        BeanUtils.copyProperties(dto,order);

        // set price and discount according to category
        order.setOrderDate(date);
        if(customer.getCategory().equals(Category.REGULAR)){
            order.setDiscount_price(order.getPrice());
            order.setDiscount_percentage(0.00);

        }else if(customer.getCategory().equals(Category.GOLD)){
            order.setDiscount_price(order.getPrice() - (order.getPrice()*0.1));
            order.setDiscount_percentage(10.00);

        }else{
            order.setDiscount_price(order.getPrice() - (order.getPrice()*0.2));
            order.setDiscount_percentage(20.00);
        }

        // saved order in database
        savedOrder = ordersDao.save(order);

        // count the orders for customers
        List<Orders> orders = ordersDao.findByCustomerId(customerId);
        int numberOfOrders = orders.size();

        // for messaging purpose about offers
        if(numberOfOrders==9){
            this.sendMailAfter9Orders();
        }
        if(numberOfOrders==19){
            this.sendMailAfter19Orders();
        }

        // change the category by the orders details
        if(numberOfOrders>=10 && numberOfOrders<20){
            customer.setCategory(Category.GOLD);
        }else if(numberOfOrders>=20){
            customer.setCategory(Category.PLATINUM);
        }

        customerDao.save(customer);
        return savedOrder;
    }

    @Override
    public List<Orders> getAllOrdersById(Integer customerId) {
        return ordersDao.findByCustomerId(customerId);
    }

    public void sendMailAfter9Orders(){
        System.out.println("You have placed 9 orders with us. Buy one more stuff and you will be promoted to Gold customer and enjoy 10% discounts!");
    }

    public void sendMailAfter19Orders(){
        System.out.println("You have placed 19 orders with us. Buy one more stuff and you will be promoted to Platinum customer and enjoy 10% discounts!");
    }
}
