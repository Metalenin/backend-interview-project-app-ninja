package com.ninjaone.backendinterviewproject.model.businessClasses;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ninjaone.backendinterviewproject.model.dto.ServiceOperatingSystemGroupCostDTO;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
@Entity
@DiscriminatorValue("service_operating_system_group_cost")
@Table(name = "service_operating_system_group_cost")
public class ServiceOperatingSystemGroupCost extends ServiceCost {
	
	@JoinColumn(name = "operating_system_group_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_serviceOperatingSystemGroupCost_operatingSystemGroup"), nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
	private OperatingSystemGroup operatingSystemGroup;
	
    public ServiceOperatingSystemGroupCost(){
    	
    }
    
    public ServiceOperatingSystemGroupCost(Service_ service_, BigDecimal cost, OperatingSystemGroup operatingSystemGroup ) {
       super(service_, cost);
       this.operatingSystemGroup = operatingSystemGroup;
    } 
    
    @JsonIgnore
    public ServiceOperatingSystemGroupCostDTO getServiceOperatingSystemGroupCostDTO() {
    	ServiceOperatingSystemGroupCostDTO serviceOperatingSystemGroupCostDTO = new ServiceOperatingSystemGroupCostDTO(this.getId(), this.getCost(), 
    			this.getService_().getId(), this.getService_().getName(), 
    			this.operatingSystemGroup.getId(), this.operatingSystemGroup.getName());
    	return serviceOperatingSystemGroupCostDTO;
    }
    
    
     
}
