package com.ninjaone.backendinterviewproject.model.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
public class CustomerServiceCostDTO {
	
    private String serviceName;
    private BigDecimal cost;
  
    public CustomerServiceCostDTO(){}
    
    public CustomerServiceCostDTO(String serviceName, BigDecimal cost) {	
        this.serviceName = serviceName;
        this.cost = cost;
    } 
      
}
