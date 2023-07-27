package com.mmt.complaintform;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComplaintFormController {
    
    @RequestMapping("/welcome")
    public String welcome(){
        return "Welcome to Complaint Form";
    }
}
