package com.example.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.UUID;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public class User {


    public UUID id;
    @NotNull
    private String name;
    @NotNull
    private String number;

    public User(@JsonProperty("id") UUID id,
                @JsonProperty("name") String name,
                @JsonProperty("number") String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public User(String name, String number) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.number = number;
    }
    public User(){}

    public UUID getId() {
        return id;
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

    public void setId(UUID id) {
        this.id=id;
    }
}