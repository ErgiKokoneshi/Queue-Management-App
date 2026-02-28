package com.example.queueless.models;

import jakarta.persistence.*;

@Entity
public class Business {
    //Fields
    @Id
    @GeneratedValue
    private Long businessId;

    @Column(nullable = false)
    private String businessName;

    @Column(nullable = false)
    private String businessLocation;

    //Constructors

    protected Business(){}


    public Business(String name, String location){
        this.businessName = name;
        this.businessLocation = location;
    }

    //Getters and Setters

    public Long getBusinessId() {
        return this.businessId;
    }

    public String getBusinessName(){
        return this.businessName;
    }

    public void setBusinessName(String businessName){
        this.businessName = businessName;
    }

    public String getBusinessLocation(){
        return this.businessLocation;
    }

    public void setBusinessLocation(String businessLocation){
        this.businessLocation = businessLocation;
    }

}
