package com.example.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;


public class User {
    private final UUID id;
    private String name;
    private String number;


    public User(@JsonProperty("id") UUID id,
                @JsonProperty("name") String name,
                @JsonProperty("number") String number){
        this.id=id;
        this.name=name;
        this.number=number;
    }


    public UUID getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName( String name){
        this.name = name;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber( String number){
        this.number = number;
    }
}