package com.example.order.service;

import com.example.order.dao.OrderDao;
import com.example.order.model.OrderItems;
import com.example.order.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderDao orderDao;

    @Autowired
    public OrderService(@Qualifier("postgres") OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public int addOrder(Orders order) {
        return orderDao.addOrder(order);
    }

    public List<Orders> getAllOrders() {
        return orderDao.getAllOrders();
    }

    public List<OrderItems> getAllItems() {
        return orderDao.getAllItems();
    }

    public int addItem( OrderItems items) {
        return orderDao.addItem( items);
    }

}

