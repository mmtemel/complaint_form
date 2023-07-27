package com.mmt.complaintform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class ComplaintFormConfiguration {
    
    @Autowired
    private ComplaintFormProperties complaintFormProperties;

    @PostConstruct
    public void init() {
        System.out.println("Display customers with complaints: "+ complaintFormProperties.isDisplayCustomersWithComplaints());
    }
}
