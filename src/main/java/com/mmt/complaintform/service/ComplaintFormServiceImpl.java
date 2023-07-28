package com.mmt.complaintform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmt.complaintform.dao.ComplaintRepository;
import com.mmt.complaintform.dao.CustomerRepository;
import com.mmt.complaintform.exception.ComplaintNotFoundException;
import com.mmt.complaintform.exception.CustomerNotFoundException;
import com.mmt.complaintform.model.Complaint;
import com.mmt.complaintform.model.Customer;

@Service
public class ComplaintFormServiceImpl implements ComplaintFormService {

    private CustomerRepository customerRepository;
    private ComplaintRepository complaintRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    @Autowired
    public void setComplaintRepository(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findCustomers(String surname) {
        return customerRepository.findBySurName(surname);
    }

    @Override
    public Customer findCustomer(Long id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id);
        if(customer == null)
            throw new CustomerNotFoundException("Customer not found with id: " + id);
        return customer;
    }

    @Override
    public void createCustomer(Customer customer) {
        customerRepository.create(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.update(customer);
    }

    @Override
    public void deleteCustomer(long id) {
        customerRepository.delete(id);
    }

    @Override
    public List<Complaint> findComplaints() {
        return complaintRepository.findAll();
    }

    @Override
    public List<Complaint> findComplaints(String subject) {
        return complaintRepository.findBySubject(subject);
    }

    @Override
    public Complaint findComplaint(long id) throws ComplaintNotFoundException {
        Complaint complaint = complaintRepository.findById(id);
        if(complaint == null)
            throw new ComplaintNotFoundException("Complaint not fount with id: " + id);
        return complaint;
    }

    @Override
    public void createComplaint(Complaint complaint) {
        complaintRepository.create(complaint);
    }

    @Override
    public void updateComplaint(Complaint complaint) {
        complaintRepository.update(complaint);
    }

    @Override
    public void deleteComplaint(long id) {
        complaintRepository.delete(id);
    }
    
}
