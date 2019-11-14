package com.example.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class OrderItems {
    private  UUID  itemId;
    private  UUID orderId;
    private int amount;

    public UUID getItemId() {
        return  itemId;
    }
    public UUID getOrderId() { return orderId; }
    public int getAmount() {
        return amount;
    }


    public OrderItems(@JsonProperty(" itemId") UUID  itemId,
                      @JsonProperty("orderId") UUID orderId,
                      @JsonProperty("amount")  int amount) {
        this. itemId =  itemId;
        this.orderId = orderId;
        this.amount = amount;
    }
}
