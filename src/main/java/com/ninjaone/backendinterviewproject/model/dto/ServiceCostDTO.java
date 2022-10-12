package com.ninjaone.backendinterviewproject.model.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceCost;
import com.ninjaone.backendinterviewproject.model.businessClasses.Service_;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
public class ServiceCostDTO {
	
	private Long id;
	private BigDecimal cost;
    
	//Service Fields
    private Long idService;
    private String nameService;
    
    public ServiceCostDTO(){}
    
    public ServiceCostDTO(Long id, BigDecimal cost, 
    		Long idService, String nameService ) {	
    	this.id = id;
    	this.cost = cost;
        this.idService = idService;
        this.nameService = nameService;
        
    }
    
    @JsonIgnore
    public ServiceCost getServiceCost() {
    	
    	Service_ service_ = new Service_(this.idService);    	
    	ServiceCost serviceCost = new ServiceCost(service_, this.cost);
  
    	return serviceCost;
    }
    
}
