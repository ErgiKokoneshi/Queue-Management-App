package com.example.queueless.models;

import jakarta.persistence.*;

@Entity
public class ServiceEntity {

    //Fields
    @Id
    @GeneratedValue
    private Long serviceId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="business_id", nullable = false)
    private Business business;

    @Column(nullable = false)
    private String serviceName;

    @Column(nullable = false)
    private int averageDurationMinutes;

    //constructors(no-arg and convenience)
    protected ServiceEntity(){}

    public ServiceEntity(String name, int duration, Business business){
        this.serviceName = name;
        this.averageDurationMinutes = duration;
        this.business = business;
    }
    
    //getters
    
    public Long getServiceId(){ return this.serviceId;}

    public Business getBusiness(){ return this.business;}
    
    public int getAverageDurationMinutes(){ return this.averageDurationMinutes;}

    public String getServiceName(){ return this.serviceName;}

    //setters

    public void setAverageDurationMinutes(int averageDurationMinutes){ this.averageDurationMinutes = averageDurationMinutes;}

    public void setServiceName(String serviceName){this.serviceName = serviceName;}
}
