package com.mmt.complaintform.web;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mmt.complaintform.model.Customer;

public class ComplaintFormRestControllerTests {
    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testGetCustomerById() {
        ResponseEntity<Customer> response = restTemplate.getForEntity("http://localhost:8080/rest/customer/1",
                Customer.class);

        MatcherAssert.assertThat(response.getStatusCode().value(), Matchers.equalTo(200));
        MatcherAssert.assertThat(response.getBody().getName(), Matchers.equalTo("Fatma"));
    }

    @Test
    public void testGetCustomerBySurName() {
        ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/customer?sn=Temel",
                List.class);

        MatcherAssert.assertThat(response.getStatusCode().value(), Matchers.equalTo(200));

        List<Map<String, String>> body = response.getBody();

        List<String> names = body.stream().map(e -> e.get("name")).collect(Collectors.toList());

        MatcherAssert.assertThat(names, Matchers.containsInAnyOrder("Fatma", "Maysa Hüma", "Kerem"));
    }

    @Test
    public void testGetCustomers() {
        ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/customers", List.class);

        MatcherAssert.assertThat(response.getStatusCode().value(), Matchers.equalTo(200));

        List<Map<String, String>> body = response.getBody();

        List<String> names = body.stream().map(e -> e.get("name")).collect(Collectors.toList());

        MatcherAssert.assertThat(names, Matchers.containsInAnyOrder("Fatma", "Maysa Hüma", "Kerem", "Sühan"));
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setName("Mert Musa");
        customer.setSurname("Temel");
        customer.setInfo("Kandıra/Kocaeli");

        URI location = restTemplate.postForLocation("http://localhost:8080/rest/customer", customer);

        Customer customer2 = restTemplate.getForObject(location, Customer.class);

        MatcherAssert.assertThat(customer2.getName(), Matchers.equalTo(customer.getName()));
        MatcherAssert.assertThat(customer2.getSurname(), Matchers.equalTo(customer.getSurname()));
        MatcherAssert.assertThat(customer2.getInfo(), Matchers.equalTo(customer.getInfo()));
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = restTemplate.getForObject("http://localhost:8080/rest/customer/4", Customer.class);

        MatcherAssert.assertThat(customer.getName(), Matchers.equalTo("Kerem"));

        customer.setName("Kerem Mıhtar");
        restTemplate.put("http://localhost:8080/rest/customer/4", customer);
        customer = restTemplate.getForObject("http://localhost:8080/rest/customer/4", Customer.class);

        MatcherAssert.assertThat(customer.getName(), Matchers.equalTo("Kerem Mıhtar"));
    }
}
