package com.ninjaone.backendinterviewproject.model.businessClasses;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ninjaone.backendinterviewproject.model.configurationClasses.BasicAudit;
import com.ninjaone.backendinterviewproject.model.dto.ServiceDeviceCostDTO;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
@Entity
@Table(name = "service_device_cost")
public class ServiceDeviceCost extends BasicAudit {
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
    //if deviceType is null, it is the cost for all devices. If present is the cost for especific device.
    @JoinColumn(name = "device_type_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_serviceDeviceCost_deviceType"), nullable = true)
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private DeviceType deviceType;
    
    @Column(name = "cost", precision = 18, scale = 2, nullable = false)
    private BigDecimal cost;

    public ServiceDeviceCost(){}
    
    public ServiceDeviceCost(DeviceType deviceType, BigDecimal cost) {
    	this.setBasicAuditory();
        this.deviceType = deviceType;
        this.cost = cost;
    } 
    
    @JsonIgnore
    public ServiceDeviceCostDTO getServiceDeviceCostDTO() {
    	Long idDeviceType = null;
    	String deviceTypeName = null;
    	if(deviceType!= null) {
    		if(deviceType.getId()!= null) {
    			idDeviceType = deviceType.getId();
    		}
    		if(deviceType.getName()!= null) {
    			deviceTypeName = deviceType.getName();
    		}
    	}
    	ServiceDeviceCostDTO serviceDeviceCostDTO = new ServiceDeviceCostDTO(id, cost, idDeviceType, deviceTypeName);
    	return serviceDeviceCostDTO;
    }
}
