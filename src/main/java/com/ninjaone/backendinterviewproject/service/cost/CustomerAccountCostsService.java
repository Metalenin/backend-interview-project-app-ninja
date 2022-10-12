package com.ninjaone.backendinterviewproject.service.cost;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.model.businessClasses.Customer;
import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceCost;
import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceOperatingSystemGroupCost;
import com.ninjaone.backendinterviewproject.model.dto.CustomerAccountCostsDTO;
import com.ninjaone.backendinterviewproject.model.dto.CustomerServiceCostDTO;
import com.ninjaone.backendinterviewproject.service.crud.CustomerCRUDService;

@Service
public class CustomerAccountCostsService {
	
	@Autowired
	CustomerCRUDService customerCRUDService;
	
	@Autowired
	CustomerDeviceCountService customerDeviceCountService;
	
	@Autowired
	CustomerServicesCostsService customerServicesCostsService;
	
	@Autowired 
	ServicesCostsService servicesCostsService;
	
    public CustomerAccountCostsDTO getCustomerCosts (Long idCustomer) {
    	
    	List<ServiceCost> servicesCostsAll = servicesCostsService.getServiceCostsFromIdCustomer (idCustomer);
    	CustomerAccountCostsDTO customerAccountCostsDTO = this.createDTOWithCustomer(idCustomer);
    	customerAccountCostsDTO = setCostPerNumberOfDevicesToDTO(customerAccountCostsDTO);
    	customerAccountCostsDTO = getCostPerServicesWithNoOS(customerAccountCostsDTO, servicesCostsAll);
    	customerAccountCostsDTO = getCostPerServicesWithOS(customerAccountCostsDTO, servicesCostsAll);
    	
    	customerAccountCostsDTO.setTotalCost();
    	
    	return customerAccountCostsDTO;
    }
    
    public CustomerAccountCostsDTO createDTOWithCustomer (Long idCustomer) {
    	
    	Optional<Customer> customerOptional = customerCRUDService.getCustomerEntity(idCustomer);
    	Customer customer = new Customer();    	
    	if(customerOptional.isPresent()) {
    		customer = customerOptional.get();
    	}    	
    	CustomerAccountCostsDTO customerAccountCostsDTO = new CustomerAccountCostsDTO(customer.getId(), customer.getIdentificationNumber(), customer.getName());
    	
    	customerAccountCostsDTO = setCustomerNumberOfDevicesToDTO(customerAccountCostsDTO);
    	
    	return customerAccountCostsDTO;
    }
    
    public CustomerAccountCostsDTO setCustomerNumberOfDevicesToDTO (CustomerAccountCostsDTO customerAccountCostsDTO) {
    	
    	Long idCustomer = customerAccountCostsDTO.getIdCustomer();
    	Long totalActiveDevicePerCustomer = customerDeviceCountService.countTotalActiveDevicePerCustomer(idCustomer);
    	customerAccountCostsDTO.setTotalDevices(totalActiveDevicePerCustomer);
    	
    	return customerAccountCostsDTO;
    }
    
    public CustomerAccountCostsDTO setCostPerNumberOfDevicesToDTO (CustomerAccountCostsDTO customerAccountCostsDTO) {
    	
    	Long totalActiveDevicePerCustomer = customerAccountCostsDTO.getTotalDevices();
    	CustomerServiceCostDTO customerServiceCostDTO = customerServicesCostsService.getCostPerServiceDevices(totalActiveDevicePerCustomer);
    	customerAccountCostsDTO.addCustomerServiceCostDTO(customerServiceCostDTO);

    	return customerAccountCostsDTO;
    }
    
    public CustomerAccountCostsDTO getCostPerServicesWithNoOS (CustomerAccountCostsDTO customerAccountCostsDTO, List<ServiceCost> servicesCostsAll){
    	
    	Long totalActiveDevicePerCustomer = customerAccountCostsDTO.getTotalDevices();
    	List<ServiceCost> servicesCosts = servicesCostsService.filterServiceCostsWithoutOSFromList(servicesCostsAll);
    	List<CustomerServiceCostDTO> customerServicesCosts = customerServicesCostsService.getCostPerServicesWithNoOS(servicesCosts, totalActiveDevicePerCustomer);
    	customerAccountCostsDTO.addListCustomerServiceCostDTO(customerServicesCosts);
    	
    	return customerAccountCostsDTO;
    }
    
    public CustomerAccountCostsDTO getCostPerServicesWithOS (CustomerAccountCostsDTO customerAccountCostsDTO, List<ServiceCost> servicesCostsAll){
    	
    	Long idCustomer = customerAccountCostsDTO.getIdCustomer();  
      	List<Long> idOperatingSystemGroups = customerDeviceCountService.getListAllSOGroupIdPerCustomer(idCustomer);
    	List<ServiceOperatingSystemGroupCost>  servicesOperatingSystemGroupCosts = servicesCostsService.filterServiceCostsWithOSFromList(servicesCostsAll);
    	HashMap<Long, Long> customerNumberOfDevicesPerOS = customerDeviceCountService.mapCountTotalActiveDevicePerCustomerAndOperatingSystemGroupList(idCustomer, idOperatingSystemGroups);
    	List<CustomerServiceCostDTO> customerServicesOSCosts = customerServicesCostsService.getCostPerServicesWithOSGrouped(servicesOperatingSystemGroupCosts, customerNumberOfDevicesPerOS);
    	customerAccountCostsDTO.addListCustomerServiceCostDTO(customerServicesOSCosts);
    	
    	return customerAccountCostsDTO;
    }
    
}
