package com.example.order.dao;

import com.example.order.model.OrderItems;
import com.example.order.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class OrderDaoService implements OrderDao {
    private final JdbcTemplate jdbcTemplate;
   @Autowired
   public OrderDaoService(JdbcTemplate jdbcTemplate) {
       this.jdbcTemplate = jdbcTemplate;
   }

    @Override
    public int addOrder( UUID id,Orders order) {
        jdbcTemplate.update(
                "INSERT INTO orders ( id, orderStatus,totalCost) VALUES (?, ?, ?)",
                id,   order.getOrderStatus(),order.getTotalCost()
        );
        return 0;
    }

    public List<Orders> getAllOrders() {
        final String sql ="SELECT id, orderStatus,totalCost FROM orders";
        return jdbcTemplate.query(sql,(resultSet ,i)-> {
            UUID id =UUID.fromString(resultSet.getString("id"));
            Integer orderStatus = resultSet.getInt("orderStatus");
            float totalCost = resultSet.getFloat("totalCost");
            return new Orders (id, orderStatus,totalCost);
        });
    }

    public List<OrderItems> getAllItems() {
        final String sql ="SELECT orderId, itemId,amount FROM orderItems";
        return jdbcTemplate.query(sql,(resultSet ,i)-> {
            UUID orderId =UUID.fromString(resultSet.getString("orderId"));
            UUID itemId =UUID.fromString(resultSet.getString("itemId"));
            Integer amount = resultSet.getInt("amount");
            return new OrderItems (orderId, itemId,amount);
        });
    }

    public int addItem(UUID  itemId, OrderItems item) {
        jdbcTemplate.update(
                "INSERT INTO orderItems(  itemId,orderId,amount) VALUES (?, ?, ?)",
                 itemId,item.getOrderId(), item.getAmount()
        );
        return 0;
    }

}
