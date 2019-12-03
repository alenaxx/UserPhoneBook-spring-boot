package com.example.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public class PhoneRecord {


    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String number;

    private UUID userId;

    public PhoneRecord(@JsonProperty("id") UUID id,
                       @JsonProperty("name") String name,
                       @JsonProperty("number") String number,
                       @JsonProperty("userId") UUID userId) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.userId = userId;
    }


    public PhoneRecord() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void securerId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
