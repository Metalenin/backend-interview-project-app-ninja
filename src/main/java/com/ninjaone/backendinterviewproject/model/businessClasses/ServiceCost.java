package com.ninjaone.backendinterviewproject.model.businessClasses;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ninjaone.backendinterviewproject.model.configurationClasses.BasicAudit;
import com.ninjaone.backendinterviewproject.model.dto.ServiceCostDTO;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
@Entity
@DiscriminatorColumn(name = "service_cost_type", discriminatorType = DiscriminatorType.STRING, length = 50)
@DiscriminatorValue("service_cost")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "service_cost")
public class ServiceCost extends BasicAudit {
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "cost", precision = 18, scale = 2, nullable = false)
    private BigDecimal cost;
    
    @JoinColumn(name = "service_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_serviceCost_service"), nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Service_ service_;
    
    public ServiceCost(){}
    
    public ServiceCost( Service_ service_, BigDecimal cost) {
    	this.setBasicAuditory();
        this.service_ = service_;
        this.cost = cost;
    } 
    
    @JsonIgnore
    public ServiceCostDTO getServiceCostDTO() {
    	ServiceCostDTO serviceCostDTO = new ServiceCostDTO(this.id, this.cost, 
    			service_.getId(), service_.getName());
    	
    	return serviceCostDTO;
    }
}
