package com.ninjaone.backendinterviewproject.model.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ninjaone.backendinterviewproject.model.businessClasses.OperatingSystemGroup;
import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceOperatingSystemGroupCost;
import com.ninjaone.backendinterviewproject.model.businessClasses.Service_;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
public class ServiceOperatingSystemGroupCostDTO extends ServiceCostDTO {
	
	//ServiceOperatingSystemGroupCost Fields
	 private Long idServiceOperatingSystemGroup;
	 private String nameServiceOperatingSystemGroup;
	
    public ServiceOperatingSystemGroupCostDTO(){}
    
    public ServiceOperatingSystemGroupCostDTO(Long id, BigDecimal cost, 
    		Long idService, String nameService,
    		Long idServiceOperatingSystemGroup, String nameServiceOperatingSystemGroup) {		
    	
        super(id, cost, idService, nameService);
        this.idServiceOperatingSystemGroup = idServiceOperatingSystemGroup;
        this.nameServiceOperatingSystemGroup = nameServiceOperatingSystemGroup;
    }
    
    @JsonIgnore
    public ServiceOperatingSystemGroupCost getServiceOperatingSystemGroupCost() {
    	
    	Service_ service_ = new Service_(this.getIdService()); 
    	OperatingSystemGroup operatingSystemGroup = new OperatingSystemGroup(this.idServiceOperatingSystemGroup);
    	
    	ServiceOperatingSystemGroupCost serviceOperatingSystemGroupCost = new ServiceOperatingSystemGroupCost(
    			service_, this.getCost(), operatingSystemGroup);
    	
    	return serviceOperatingSystemGroupCost;
    }
    
}
