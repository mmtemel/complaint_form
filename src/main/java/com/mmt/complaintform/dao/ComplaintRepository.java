package com.mmt.complaintform.dao;

import java.util.List;

import com.mmt.complaintform.model.Complaint;

public interface ComplaintRepository {
    List<Complaint> findAll();
    Complaint findById(long id);
    List<Complaint> findByEmployerId(long id);
    List<Complaint> findByCustomerId(long id);
    List<Complaint> findBySubject(String subject);
    List<Complaint> findByEvaluation(boolean evaluated);
    void create(Complaint complaint);
    Complaint update(Complaint complaint);
    void delete(Long id);
}
