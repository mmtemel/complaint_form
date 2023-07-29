package com.mmt.complaintform.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mmt.complaintform.service.ComplaintFormService;

@Controller
public class ComplaintFormController {

    @Autowired
    private ComplaintFormService complaintFormService;
    
    @RequestMapping("/customers")
    public ModelAndView getCustomers() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("customers", complaintFormService.findCustomers());
        mav.setViewName("customers");
        return mav;
    }

    @RequestMapping("/complaints")
    public ModelAndView getComplaints() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("complaints", complaintFormService.findComplaints());
        mav.setViewName("complaints");
        return mav;
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "Welcome to Complaint Form";
    }
}
