package com.ninjaone.backendinterviewproject.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ninjaone.backendinterviewproject.model.businessClasses.Customer;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
public class CustomerDTO {
	
    private Long id;    
    private String identificationNumber;    
    private String name;
  

    public CustomerDTO(){}
    
    public CustomerDTO(Long id, String identificationNumber, String name) {	
    	this.id = id;
        this.identificationNumber = identificationNumber;
        this.name = name.toUpperCase();
    } 
    
    @JsonIgnore
    public Customer getCustomerForCreation () {
    	Customer customer= new Customer(identificationNumber, name);
    	return customer;
    }
}
