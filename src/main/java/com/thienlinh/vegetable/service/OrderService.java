package com.thienlinh.vegetable.service;

import com.thienlinh.vegetable.model.Order;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);
    Order findById(int id);
    List<Order> listOfOrders();

}
