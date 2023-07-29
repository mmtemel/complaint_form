package com.mmt.complaintform.dao.mem;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.mmt.complaintform.dao.ComplaintRepository;
import com.mmt.complaintform.model.Complaint;

@Repository
public class ComplaintRepositooryInMemoryImpl implements ComplaintRepository{

    private Map<Long, Complaint> compliantsMap = new HashMap<>();

    public ComplaintRepositooryInMemoryImpl(){
        Complaint complaint1 = new Complaint();
        complaint1.setId(1L);
        complaint1.setSubject("Quality");
        complaint1.setDescription("bad quality");
        complaint1.setEvaluated(false);

        Complaint complaint2 = new Complaint();
        complaint2.setId(2L);
        complaint2.setSubject("Service");
        complaint2.setDescription("bad service");
        complaint2.setEvaluated(false);

        Complaint complaint3 = new Complaint();
        complaint3.setId(3L);
        complaint3.setSubject("Communication");
        complaint3.setDescription("bad communication");
        complaint3.setEvaluated(false);

        Complaint complaint4 = new Complaint();
        complaint4.setId(4L);
        complaint4.setSubject("Employer");
        complaint4.setDescription("bad employer");
        complaint4.setEvaluated(false);

        compliantsMap.put(complaint1.getId(), complaint1);
        compliantsMap.put(complaint2.getId(), complaint2);
        compliantsMap.put(complaint3.getId(), complaint3);
        compliantsMap.put(complaint4.getId(), complaint4);
    }

        @Override
    public List<Complaint> findAll() {
        return new ArrayList<>(compliantsMap.values());
    }

    @Override
    public Complaint findById(long id) {
        return compliantsMap.get(id);
    }

    @Override
    public List<Complaint> findByEmployerId(long id) {
        return compliantsMap.values().stream().filter(o->o.getEmployerId()==id).collect(Collectors.toList());
    }
    
    @Override
    public List<Complaint> findByCustomerId(long id) {
        return compliantsMap.values().stream().filter(o->o.getCustomer().getId()==id).collect(Collectors.toList());
    }
    
    @Override
    public List<Complaint> findBySubject(String subject) {
        return compliantsMap.values().stream().filter(o->o.getSubject().equals(subject)).collect(Collectors.toList());
    }
    
    @Override
    public List<Complaint> findByEvaluation(boolean evaluated) {
        return compliantsMap.values().stream().filter(o->o.isEvaluated()==evaluated).collect(Collectors.toList());
    }

    @Override
    public void create(Complaint complaint) {
        complaint.setId(new Date().getTime());
        compliantsMap.put(complaint.getId(), complaint);
    }

    @Override
    public Complaint update(Complaint complaint) {
        compliantsMap.replace(complaint.getId(), complaint);
        return complaint;
    }

    @Override
    public void delete(Long id) {
        compliantsMap.remove(id);
    }
}
