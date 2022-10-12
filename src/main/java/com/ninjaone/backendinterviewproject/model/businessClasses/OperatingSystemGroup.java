package com.ninjaone.backendinterviewproject.model.businessClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import com.ninjaone.backendinterviewproject.model.configurationClasses.BasicAudit;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
@Entity
@Table(name = "operating_system_group")
public class OperatingSystemGroup extends BasicAudit {
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name" , length = 100, nullable = false, unique=true)
    private String name;

    public OperatingSystemGroup(){}
    
    public OperatingSystemGroup(Long id){
    	this.id = id;
    }
    
    public OperatingSystemGroup(String name) {
    	this.setBasicAuditory();
        this.name = name.toUpperCase();
    } 
}
