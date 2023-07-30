package com.mmt.complaintform.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mmt.complaintform.exception.CustomerNotFoundException;
import com.mmt.complaintform.exception.InternalServerException;
import com.mmt.complaintform.model.Complaint;
import com.mmt.complaintform.model.Customer;
import com.mmt.complaintform.service.ComplaintFormService;

@RestController
@RequestMapping("/rest")
public class ComplaintFormRestController {

    @Autowired
    private ComplaintFormService complaintFormService;

    //cutomer get
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

    /* @RequestMapping(method = RequestMethod.GET, value = "/customer/{id}",produces="application/json")
	public ResponseEntity<?> getCustomerAsHateoasEntityModel(@PathVariable("id") Long id) {
		try {
			Customer customer = complaintFormService.findCustomer(id);
			Link self = WebMvcLinkBuilder.linkTo(ComplaintFormRestController.class).slash("/customer/" + id).withSelfRel();
			Link create = WebMvcLinkBuilder.linkTo(ComplaintFormRestController.class).slash("/customer").withRel("create");
			Link update = WebMvcLinkBuilder.linkTo(ComplaintFormRestController.class).slash("/customer/" + id).withRel("update");
			Link delete = WebMvcLinkBuilder.linkTo(ComplaintFormRestController.class).slash("/customer/" + id).withRel("delete");
			EntityModel<Customer> resource = new EntityModel<Customer>(customer, self, create, update, delete);
			return ResponseEntity.ok(resource);
		} catch (CustomerNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
	} */

    //complaint get
    @RequestMapping(method = RequestMethod.GET, value="/complaints")
    public ResponseEntity<List<Complaint>> getComplaints() {
        List<Complaint> complaints = complaintFormService.findComplaints();
        return ResponseEntity.ok(complaints);
    }

    @RequestMapping(method = RequestMethod.GET, value="/complaint")
    public ResponseEntity<List<Complaint>> getComplaints(@RequestParam("sb") String subject) {
        List<Complaint> complaints = complaintFormService.findComplaints(subject);
        return ResponseEntity.ok(complaints);
    }

    @RequestMapping(method = RequestMethod.GET, value="/complaint/{id}")
    public ResponseEntity<Complaint> getComplaint(@PathVariable("id") long id) {
        try{
            Complaint complaint = complaintFormService.findComplaint(id);
            return ResponseEntity.ok(complaint);
        } catch(CustomerNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    //customer post
    @RequestMapping(method=RequestMethod.POST,value="/customer")
    public ResponseEntity<URI> createCustomer(@RequestBody Customer customer) {
        try {
            complaintFormService.createCustomer(customer);
            Long id = customer.getId();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        }
		 catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //customer put
    @RequestMapping(method = RequestMethod.PUT, value = "/customer/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customerRequest) {
		try {
			Customer customer = complaintFormService.findCustomer(id);
			customer.setName(customerRequest.getName());
			customer.setSurname(customerRequest.getSurname());
			complaintFormService.updateCustomer(customer);
			return ResponseEntity.ok().build();
		} catch (CustomerNotFoundException ex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

    //customer delete
    /* @RequestMapping(method = RequestMethod.DELETE, value = "/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
		try {
			complaintFormService.deleteCustomer(id);
            return ResponseEntity.ok().build();
		} catch (CustomerNotFoundException ex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	} */

    //customer delete 2
    @RequestMapping(method = RequestMethod.DELETE, value = "/customer/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
		try {
			complaintFormService.findCustomer(id);
			complaintFormService.deleteCustomer(id);
		} catch (CustomerNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalServerException(ex);
		}
	}
}
