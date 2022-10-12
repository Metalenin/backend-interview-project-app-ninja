package com.ninjaone.backendinterviewproject.model.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ninjaone.backendinterviewproject.model.businessClasses.DeviceType;
import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceDeviceCost;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
public class ServiceDeviceCostDTO {
	
	private Long id;
	private BigDecimal cost;
    
	//DeviceType Fields
    //if deviceType is null, it is the cost for all devices. If present is the cost for especific device.
    private Long idDeviceType;
    private String nameDeviceType;
    
    public ServiceDeviceCostDTO(){}
    
    public ServiceDeviceCostDTO(Long id,BigDecimal cost, 
    		Long idDeviceType, String nameDeviceType ) {	
    	
    	this.id = id;
    	this.cost = cost;
    	
        this.idDeviceType = idDeviceType;
        this.nameDeviceType = nameDeviceType;
       
    }
    
    @JsonIgnore
    public ServiceDeviceCost getServiceDeviceCost() {
    	DeviceType deviceType = null;
    	
    	if(this.idDeviceType != null) {
    		deviceType = new DeviceType();
    		deviceType.setId(this.idDeviceType );
    	}
    	
    	ServiceDeviceCost serviceDeviceCost = new ServiceDeviceCost(deviceType, this.cost);
    	return serviceDeviceCost;
    }
    
}
