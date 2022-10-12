package com.ninjaone.backendinterviewproject.model.businessClasses;

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

import com.ninjaone.backendinterviewproject.model.configurationClasses.BasicAudit;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
@Entity
@Table(name = "device")
public class Device extends BasicAudit {
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "system_name" , length = 150, nullable = false, unique=true)
    private String systemName;
    
    @JoinColumn(name = "device_type_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_device_deviceType"), nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private DeviceType deviceType;

    public Device(){}
    
    public Device(Long id) {
        this.id = id;
    } 
    
    public Device(String systemName, DeviceType deviceType) {
    	this.setBasicAuditory();
        this.systemName = systemName.toUpperCase();
        this.deviceType = deviceType;
    } 
}
