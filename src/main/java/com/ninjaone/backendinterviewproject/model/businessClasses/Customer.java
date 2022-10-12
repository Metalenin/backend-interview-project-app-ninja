package com.ninjaone.backendinterviewproject.model.businessClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ninjaone.backendinterviewproject.model.configurationClasses.BasicAudit;
import com.ninjaone.backendinterviewproject.model.dto.CustomerDTO;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
@Entity
@Table(name = "customer")
public class Customer extends BasicAudit {
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "identification_number" , length = 50, nullable = false, unique=true)
    private String identificationNumber;
    
    @Column(name = "name" , length = 250, nullable = false)
    private String name;
  
    public Customer(){}
    
    public Customer(Long id) {
    	this.id = id;
    } 
    
    public Customer(String identificationNumber, String name) {
    	
    	this.setBasicAuditory();
        this.identificationNumber = identificationNumber;
        this.name = name.toUpperCase();
       
    } 
    
    @JsonIgnore
    public CustomerDTO getCustomerDTO () {
    	CustomerDTO customerDTO = new CustomerDTO(this.id, this.identificationNumber, this.name);
    	return customerDTO;
    }
}
