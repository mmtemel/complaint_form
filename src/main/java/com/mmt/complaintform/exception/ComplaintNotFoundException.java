package com.mmt.complaintform.exception;

public class ComplaintNotFoundException extends RuntimeException{

    public ComplaintNotFoundException(String message){
        super(message);
    }
}
