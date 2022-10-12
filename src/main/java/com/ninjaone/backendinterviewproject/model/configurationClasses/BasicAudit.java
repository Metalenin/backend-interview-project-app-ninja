package com.ninjaone.backendinterviewproject.model.configurationClasses;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ninjaone.backendinterviewproject.model.enumeration.ActiveEnum;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
@MappedSuperclass
public abstract class BasicAudit {
	
    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
   
	@Column(name = "is_active")
    private Boolean isActive;
	
	public void setBasicAuditory() {
		this.setActive();
		this.creationDate = new Date();
	}
	
	public void setDeleted() {
		this.isActive = ActiveEnum.NO.getValue();
	}
	
	public void setActive() {
		this.isActive = ActiveEnum.YES.getValue();
	}
    
}
