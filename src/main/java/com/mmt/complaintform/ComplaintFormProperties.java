package com.mmt.complaintform;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="complaintform")
public class ComplaintFormProperties {
    private boolean displayCustomersWithComplaints = false;

    public boolean isDisplayCustomersWithComplaints() {
        return displayCustomersWithComplaints;
    }

    public void setDisplayCustomersWithComplaints(boolean displayCustomersWithComplaints) {
        this.displayCustomersWithComplaints = displayCustomersWithComplaints;
    }
}
