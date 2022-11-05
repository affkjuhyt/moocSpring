package com.thienlinh.vegetable.service.impl;

import com.thienlinh.vegetable.domain.OrderRepository;
import com.thienlinh.vegetable.model.Order;
import com.thienlinh.vegetable.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> listOfOrders() {
        return orderRepository.listOfOrders();
    }

    @Override
    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    @Override
    public Order findById(int id) {
        return orderRepository.findById(id);
    }
}
