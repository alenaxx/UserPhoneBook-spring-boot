package com.example.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Orders {
    private  UUID id;
    private int orderStatus;
    private float totalCost;

    public UUID getId() {
        return id;
    }


    public int getOrderStatus() {
        return orderStatus;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public Orders(@JsonProperty("id") UUID id,
                  @JsonProperty("orderStatus") int orderStatus,
                  @JsonProperty("totalCost") float totalCost) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.totalCost = totalCost;
    }
}