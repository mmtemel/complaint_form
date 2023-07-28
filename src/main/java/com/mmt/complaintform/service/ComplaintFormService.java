package com.mmt.complaintform.service;

import java.util.List;

import com.mmt.complaintform.exception.ComplaintNotFoundException;
import com.mmt.complaintform.exception.CustomerNotFoundException;
import com.mmt.complaintform.model.Complaint;
import com.mmt.complaintform.model.Customer;

public interface ComplaintFormService {
    List<Customer> findCustomers();
    List<Customer> findCustomers(String surname);
    Customer findCustomer(Long id) throws CustomerNotFoundException;
    void createCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(long id);

    List<Complaint> findComplaints();
    List<Complaint> findComplaints(String subject);
    Complaint findComplaint(long id) throws ComplaintNotFoundException;
    void createComplaint(Complaint complaint);
    void updateComplaint(Complaint complaint);
    void deleteComplaint(long id);
}
