package com.ninjaone.backendinterviewproject.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ninjaone.backendinterviewproject.model.businessClasses.Customer;
import com.ninjaone.backendinterviewproject.model.businessClasses.CustomerService;
import com.ninjaone.backendinterviewproject.model.businessClasses.Service_;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
public class CustomerServiceDTO {
	
	private Long id;    
 
    //Customer Fields
    private Long idCustomer;  
    private String customerIdentificationNumber;    
    private String customerName;
    
    //Service Fields
    private Long idService;
    private String serviceName;

    public CustomerServiceDTO(){}
    
    public CustomerServiceDTO(Long id,
    		Long idCustomer, String customerIdentificationNumber, String customerName, 
    		Long idService, String serviceName ) {

        this.id = id;
       
        this.idCustomer = idCustomer;  
        this.customerIdentificationNumber = customerIdentificationNumber;    
        this.customerName = customerName;
        
        this.idService = idService;
        this.serviceName = serviceName;
    } 
   
   @JsonIgnore
   public CustomerService getCustomerService() {
	   
	   Customer customer = new Customer(this.idCustomer);
	   Service_ service_ = new Service_(this.idService);
	   CustomerService customerService = new CustomerService(customer, service_);
	   
	return customerService;
	   
   }
}
