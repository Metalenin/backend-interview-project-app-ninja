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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ninjaone.backendinterviewproject.model.configurationClasses.BasicAudit;
import com.ninjaone.backendinterviewproject.model.dto.CustomerServiceDTO;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
@Entity
@Table(name = "customer_service", uniqueConstraints = { @UniqueConstraint(columnNames = { "customer_id", "service_id" }) })

public class CustomerService extends BasicAudit {
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JoinColumn(name = "customer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_customerService_customer"), nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Customer customer;
    
    @JoinColumn(name = "service_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_customerService_service"), nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Service_ service_;

    public CustomerService(){}
    
    public CustomerService( Customer customer, Service_ service_) {
    	this.setBasicAuditory();
        this.customer = customer;
        this.service_ = service_;
    } 
    
    @JsonIgnore
    public CustomerServiceDTO getCustomerServiceDTO() {
    	CustomerServiceDTO customerServiceDTO = new CustomerServiceDTO(this.id, 
    			customer.getId(), customer.getIdentificationNumber(), customer.getName(), 
    			service_.getId(), service_.getName());
    	return customerServiceDTO;
    }
}
