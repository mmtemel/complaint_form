package com.mmt.complaintform.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmt.complaintform.exception.CustomerNotFoundException;
import com.mmt.complaintform.model.Customer;
import com.mmt.complaintform.service.ComplaintFormService;

@RestController
@RequestMapping("/rest")
public class ComplaintFormRestController {

    @Autowired
    private ComplaintFormService complaintFormService;

    @RequestMapping(method = RequestMethod.GET, value="/customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> customers = complaintFormService.findCustomers();
        return ResponseEntity.ok(customers);
    }

    @RequestMapping(method = RequestMethod.GET, value="/customer")
    public ResponseEntity<List<Customer>> getCustomers(@RequestParam("sn") String surname) {
        List<Customer> customers = complaintFormService.findCustomers(surname);
        return ResponseEntity.ok(customers);
    }

    @RequestMapping(method = RequestMethod.GET, value="/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) {
        try{
            Customer customer = complaintFormService.findCustomer(id);
            return ResponseEntity.ok(customer);
        } catch(CustomerNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
