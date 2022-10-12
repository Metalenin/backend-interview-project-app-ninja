package com.ninjaone.backendinterviewproject.model.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ninjaone.backendinterviewproject.model.businessClasses.Customer;
import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceCost;
import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceOperatingSystemGroupCost;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
public class CustomerAccountCostParametersDTO {
	
	private Long idCustomer;    
    private String identificationNumber;    
    private String nameCustomer;
    
    private Long customerNumberOfDevices;
    
    private List<ServiceCost> serviceCostsWithoutOS;
    private List<ServiceOperatingSystemGroupCost> serviceCostsWithtOS;
    
    private Map<Long, Long> customerNumberOfDevicesPerOS;
	
    public CustomerAccountCostParametersDTO(){}
    
    public CustomerAccountCostParametersDTO(Customer customer){
    	this.idCustomer = customer.getId();
    	this.identificationNumber = customer.getIdentificationNumber();
    	this.nameCustomer = customer.getName();
    }
    
    
    
  
    
}
