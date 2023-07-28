package com.mmt.complaintform.dao.mem;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.mmt.complaintform.dao.CustomerRepository;
import com.mmt.complaintform.model.Customer;

@Repository
public class CustomerRepositoryInMemoryImpl implements CustomerRepository{

    private Map<Long, Customer> customersMap = new HashMap<>();

    public CustomerRepositoryInMemoryImpl(){
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("Fatma");
        customer1.setSurname("Temel");
        customer1.setInfo("İzmit/Kocaeli");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setName("Maysa Hüma");
        customer2.setSurname("Temel");
        customer2.setInfo("İzmit/Kocaeli");

        Customer customer3 = new Customer();
        customer3.setId(3L);
        customer3.setName("Sühan");
        customer3.setSurname("Aykaç");
        customer3.setInfo("Akçakoca/Düzce");

        Customer customer4 = new Customer();
        customer4.setId(4L);
        customer4.setName("Kerem");
        customer4.setSurname("Temel");
        customer4.setInfo("Kandıra/Kocaeli");

        customersMap.put(customer1.getId(), customer1);
        customersMap.put(customer2.getId(), customer2);
        customersMap.put(customer3.getId(), customer3);
        customersMap.put(customer4.getId(), customer4);
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customersMap.values());
    }

    @Override
    public Customer findById(long id) {
        return customersMap.get(id);
    }

    @Override
    public List<Customer> findBySurName(String surname) {
        return customersMap.values().stream().filter(o->o.getSurname().equals(surname)).collect(Collectors.toList());
    }

    @Override
    public void create(Customer customer) {
        customer.setId(new Date().getTime());
        customersMap.put(customer.getId(), customer);
    }

    @Override
    public Customer update(Customer customer) {
        customersMap.replace(customer.getId(), customer);
        return customer;
    }

    @Override
    public void delete(long id) {
        customersMap.remove(id);
    }

}