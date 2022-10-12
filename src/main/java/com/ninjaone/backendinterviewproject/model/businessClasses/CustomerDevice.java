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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ninjaone.backendinterviewproject.model.configurationClasses.BasicAudit;
import com.ninjaone.backendinterviewproject.model.dto.CustomerDeviceDTO;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
@Entity
@Table(name = "customer_device")
public class CustomerDevice extends BasicAudit {
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "customer_device_name" , length = 200, nullable = false)
    private String customerDevicename;
    
    @Column(name = "serial_number" , length = 100, nullable = false, unique=true)
    private String serialNumber;
    
    @Column(name = "description" , length = 300, nullable = true)
    private String description;
    
    @JoinColumn(name = "customer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_customerDevice_customer"), nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Customer customer;
    
    @JoinColumn(name = "device_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_customerDevice_device"), nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Device device;

    public CustomerDevice(){}
    
    public CustomerDevice(String customerDevicename, String serialNumber, String description,Customer customer, Device device) {
    	this.setBasicAuditory();
        this.customerDevicename = customerDevicename;
        this.serialNumber = serialNumber;
        this.description = description;
        this.customer = customer;
        this.device = device;
    } 
    
    @JsonIgnore
    public CustomerDeviceDTO getCustomerDeviceDTO() {
    	CustomerDeviceDTO customerDeviceDTO = new CustomerDeviceDTO(this.id, this.customerDevicename, this.serialNumber, this.description, 
    			customer.getId(), customer.getIdentificationNumber(), customer.getName(), 
    			device.getId(), device.getSystemName());
    	
    	return customerDeviceDTO;
    }
    
}
