package com.mmt.complaintform.model;

public class Complaint {
    private long id;
    private String subject;
    private String description;
    private String customerFeedback;
    private boolean evaluated;

    private Customer customer;
    private long employerId; //

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCustomerFeedback() {
        return customerFeedback;
    }
    public void setCustomerFeedback(String customerFeedback) {
        this.customerFeedback = customerFeedback;
    }
    public boolean isEvaluated() {
        return evaluated;
    }
    public void setEvaluated(boolean evaluated) {
        this.evaluated = evaluated;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public long getEmployerId() {
        return employerId;
    }
    public void setEmployerId(long employerId) {
        this.employerId = employerId;
    }

    @Override
    public String toString() {
        return "Complaint [id=" + id + ", subject=" + subject + ", description=" + description + ", customerFeedback="
                + customerFeedback + ", evaluated=" + evaluated + ", customer=" + customer + ", employerId="
                + employerId + "]";
    }
}
