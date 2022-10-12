package com.ninjaone.backendinterviewproject.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
public class CustomerAccountCostsDTO {
	
    private Long idCustomer;    
    private String identificationNumber;    
    private String name;
    
    private Long totalDevices; 
    
    private BigDecimal totalCost;
  
    private List<CustomerServiceCostDTO> customerServicesCosts;

    public CustomerAccountCostsDTO(){}
    
    public CustomerAccountCostsDTO(Long idCustomer, String identificationNumber, String name) {	
    	this.idCustomer = idCustomer;
        this.identificationNumber = identificationNumber;
        this.name = name.toUpperCase();
        this.customerServicesCosts = new ArrayList<CustomerServiceCostDTO>();
    } 
    
    public void setTotalCost() {
    	this.totalCost = customerServicesCosts
    			.stream()
    			.map(csc -> csc.getCost())
    			.reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public void addCustomerServiceCostDTO (CustomerServiceCostDTO customerServiceCostDTO) {
    	if(this.customerServicesCosts == null) {
    		 this.customerServicesCosts = new ArrayList<CustomerServiceCostDTO>();
    	}
    	this.customerServicesCosts.add(customerServiceCostDTO);
    }
    
    public void addListCustomerServiceCostDTO (List<CustomerServiceCostDTO> customersServiceCostsDTO) {
    	if(this.customerServicesCosts == null) {
    		 this.customerServicesCosts = new ArrayList<CustomerServiceCostDTO>();
    	}
    	this.customerServicesCosts.addAll(customersServiceCostsDTO);
    }
    
}
