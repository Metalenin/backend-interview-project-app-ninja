package com.ninjaone.backendinterviewproject.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ninjaone.backendinterviewproject.model.businessClasses.Customer;
import com.ninjaone.backendinterviewproject.model.businessClasses.CustomerDevice;
import com.ninjaone.backendinterviewproject.model.businessClasses.Device;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
public class CustomerDeviceDTO {
	
	private Long id;    
    private String customerDevicename;   
    private String serialNumber;    
    private String description;

    //Customer Fields
    private Long idCustomer;  
    private String customerIdentificationNumber;    
    private String customerName;
    
    //Device Fields
    private Long idDevice;
    private String deviceSystemName;

    public CustomerDeviceDTO(){}
    
    public CustomerDeviceDTO(Long id, String customerDevicename, String serialNumber, String description,
    		Long idCustomer, String customerIdentificationNumber, String customerName, 
    		Long idDevice, String deviceSystemName ) {

    	this.id = id;
        this.customerDevicename = customerDevicename;
        this.serialNumber = serialNumber;
        this.description = description;
       
        this.idCustomer = idCustomer;  
        this.customerIdentificationNumber = customerIdentificationNumber;    
        this.customerName = customerName;
        
        this.idDevice = idDevice;
        this.deviceSystemName = deviceSystemName;
    } 
   
    @JsonIgnore
   public CustomerDevice getCustomerDevice() {
	   
	   Customer customer = new Customer(this.idCustomer);
	   Device device = new Device(this.idDevice);
	   CustomerDevice customerDevice = new CustomerDevice(customerDevicename, serialNumber, description, customer, device);
	   
	return customerDevice;
	   
   }
}
