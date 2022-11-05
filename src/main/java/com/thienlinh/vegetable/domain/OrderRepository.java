package com.thienlinh.vegetable.domain;

import com.thienlinh.vegetable.model.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> listOfOrders();

    void addOrder(Order order);

    Order findById(int id);
}
