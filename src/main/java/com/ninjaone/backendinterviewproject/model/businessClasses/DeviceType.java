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
@Table(name = "device_type")
public class DeviceType extends BasicAudit {
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name" , length = 150, nullable = false, unique=true)
    private String name;
    
    @JoinColumn(name = "operating_system_group_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_deviceType_operatingSystemGroup"), nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private OperatingSystemGroup operatingSystemGroup;

    public DeviceType(){}
    
    public DeviceType( String name, OperatingSystemGroup operatingSystemGroup) {
    	this.setBasicAuditory();
        this.name = name.toUpperCase();
        this.operatingSystemGroup = operatingSystemGroup;
    } 
}
