package com.example.order.api;

import com.example.order.model.OrderItems;
import com.example.order.model.Orders;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("order")
@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService ;
    }

    @PostMapping(path = "/ord")
    public void addOrder(@RequestBody Orders order) {
       orderService.addOrder(order);
    }

    @GetMapping(path = "/ord")
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
    }
    @GetMapping(path = "/item")
    public List<OrderItems> getAllItems() {
        return orderService.getAllItems();
    }
    @PostMapping(path = "/item")
    public void addItem( @RequestBody OrderItems item) {
       orderService.addItem(item);
    }

}