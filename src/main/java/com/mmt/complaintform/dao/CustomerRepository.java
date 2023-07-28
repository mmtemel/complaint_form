package com.mmt.complaintform.dao;

import java.util.List;

import com.mmt.complaintform.model.Customer;

public interface CustomerRepository {
    List<Customer> findAll();
    Customer findById(long id);
    List<Customer> findBySurName(String surname);
    void create(Customer customer);
    Customer update(Customer customer);//jpa hibernate gibi teknolojilerin güncelleme işleminde parametre olarak verilen entity ile
                                        //döndürülen entity'nin farklı olması nedeniyledir. Jdbc api'de referans değişmesi söz konusu değil.
    void delete(long id);
}
