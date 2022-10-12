package com.ninjaone.backendinterviewproject.service.cost;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceCost;
import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceDeviceCost;
import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceOperatingSystemGroupCost;
import com.ninjaone.backendinterviewproject.model.dto.CustomerServiceCostDTO;
import com.ninjaone.backendinterviewproject.service.crud.ServiceDeviceCostCRUDService;

@Service
public class CustomerServicesCostsService {
	
	@Autowired ServiceDeviceCostCRUDService serviceDeviceCostCRUDService;
	private final String costPerDeviceServiceName = "Devices";
	
	public CustomerServiceCostDTO getCostPerServiceDevices (Long totalActiveDevicePerCustomer){
		
		ServiceDeviceCost serviceDeviceCost = serviceDeviceCostCRUDService.getServiceDeviceCostGeneralEntity();
		BigDecimal totalDevicesBigDecimal = BigDecimal.valueOf(totalActiveDevicePerCustomer);
		BigDecimal servicesDevicesCost = serviceDeviceCost.getCost().multiply(totalDevicesBigDecimal);
		CustomerServiceCostDTO customerServiceCostDTO = new CustomerServiceCostDTO(costPerDeviceServiceName, servicesDevicesCost);
			
    	return customerServiceCostDTO;
    }
	
    public List<CustomerServiceCostDTO> getCostPerServicesWithNoOS (List<ServiceCost> servicesCosts, Long totalActiveDevicePerCustomer){
    	
    	List<CustomerServiceCostDTO> customerServicesCosts = new ArrayList<CustomerServiceCostDTO>();
    	
    	if(servicesCosts!= null && !servicesCosts.isEmpty() && totalActiveDevicePerCustomer!= null) {
    		BigDecimal totalDevicesBigDecimal = BigDecimal.valueOf(totalActiveDevicePerCustomer);
    		
    		for(ServiceCost serviceCost: servicesCosts) {
    			BigDecimal servicesDevicesCost = serviceCost.getCost().multiply(totalDevicesBigDecimal);
    			CustomerServiceCostDTO customerServiceCostDTO = new CustomerServiceCostDTO(serviceCost.getService_().getName(), servicesDevicesCost);
    			customerServicesCosts.add(customerServiceCostDTO);
    		}
    	}
    	return customerServicesCosts;
    }
    
    
    public List<CustomerServiceCostDTO> getCostPerServicesWithOS (List<ServiceOperatingSystemGroupCost> servicesOperatingSystemGroupCosts, 
    		HashMap<Long, Long> customerNumberOfDevicesPerOS){
    	List<CustomerServiceCostDTO> customerServicesCosts = new ArrayList<CustomerServiceCostDTO>();
    	if(servicesOperatingSystemGroupCosts!= null && !servicesOperatingSystemGroupCosts.isEmpty() && customerNumberOfDevicesPerOS != null) {
    		
    		for(ServiceOperatingSystemGroupCost serviceOperatingSystemGroupCost: servicesOperatingSystemGroupCosts) {
    			Long idOperatingSystemGroup = serviceOperatingSystemGroupCost.getOperatingSystemGroup().getId();
    			if(customerNumberOfDevicesPerOS.containsKey(idOperatingSystemGroup)) {
    				BigDecimal totalDevicesBigDecimal = BigDecimal.valueOf(customerNumberOfDevicesPerOS.get(idOperatingSystemGroup));
	    			BigDecimal servicesDevicesOSCost = serviceOperatingSystemGroupCost.getCost().multiply(totalDevicesBigDecimal);
	    			CustomerServiceCostDTO customerServiceCostDTO = new CustomerServiceCostDTO(serviceOperatingSystemGroupCost.getService_().getName(), servicesDevicesOSCost);
	    			customerServicesCosts.add(customerServiceCostDTO);
    			}	
    		}
    	}
    	return customerServicesCosts;
    }
    
    
    public List<CustomerServiceCostDTO> groupCostsPerServicesWithOS (List<CustomerServiceCostDTO> customerServicesCosts){
    	List<CustomerServiceCostDTO> customerServicesCostsGrouped = new ArrayList<CustomerServiceCostDTO>();
             
             Map<String, BigDecimal> mapGroupedCosts = customerServicesCosts
            		 .stream()
                     .collect(Collectors.groupingBy(CustomerServiceCostDTO::getServiceName, 
                              Collectors.mapping(CustomerServiceCostDTO::getCost, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));  
             
             for (Map.Entry<String, BigDecimal> entry : mapGroupedCosts.entrySet()) {
            	 CustomerServiceCostDTO customerServiceCostDTO = new CustomerServiceCostDTO(entry.getKey(), entry.getValue());
            	 customerServicesCostsGrouped.add(customerServiceCostDTO);
             }
    	return customerServicesCostsGrouped;
    }
    
    
    public List<CustomerServiceCostDTO> getCostPerServicesWithOSGrouped (List<ServiceOperatingSystemGroupCost> servicesOperatingSystemGroupCosts, 
    		HashMap<Long, Long> customerNumberOfDevicesPerOS){
    	List<CustomerServiceCostDTO> customerServicesCosts = this.getCostPerServicesWithOS(servicesOperatingSystemGroupCosts, customerNumberOfDevicesPerOS);
    	List<CustomerServiceCostDTO> customerServicesCostsGrouped = this.groupCostsPerServicesWithOS(customerServicesCosts);
    	
    	return customerServicesCostsGrouped;
    }
      
}
