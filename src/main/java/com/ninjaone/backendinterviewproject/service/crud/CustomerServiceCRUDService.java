package com.ninjaone.backendinterviewproject.service.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.CustomerServiceRepository;
import com.ninjaone.backendinterviewproject.model.businessClasses.CustomerService;
import com.ninjaone.backendinterviewproject.model.dto.CustomerServiceDTO;
import com.ninjaone.backendinterviewproject.model.enumeration.ActiveEnum;

@Service
public class CustomerServiceCRUDService {
	
    private final CustomerServiceRepository customerServiceRepository;
    private static final Boolean active = ActiveEnum.YES.getValue();

    public CustomerServiceCRUDService(CustomerServiceRepository customerServiceRepository) {
        this.customerServiceRepository = customerServiceRepository;
    }

    public CustomerService saveCustomerServiceEntity(CustomerService customerService){
        return customerServiceRepository.save(customerService);
    }

    public Optional<CustomerService> getCustomerServiceEntity(Long id){
        return customerServiceRepository.findByIdAndIsActive(id, active);
    }
    
    public void deleteCustomerServiceEntity(Long id) {
    	Optional<CustomerService> customerServiceOptional = customerServiceRepository.findById(id);
    	
    	if(customerServiceOptional.isPresent()) {
    		CustomerService customerService = customerServiceOptional.get();
    		customerService.setDeleted();
    		customerServiceRepository.save(customerService);
    	}
    }
    
    public List<CustomerService> getAllCustomerServiceActive (){
    	List<CustomerService> customerServices = customerServiceRepository.findByIsActive(active);
    	if(customerServices == null) {
    		customerServices = new ArrayList<CustomerService>();
    	}
    	return customerServices;
    }
    
    public List<CustomerServiceDTO> getAllCustomerServiceActiveDTO(){
    	List<CustomerService> customerServices = getAllCustomerServiceActive();
    	List<CustomerServiceDTO> customerServicesDTO = new ArrayList<CustomerServiceDTO>();
    	if(!customerServicesDTO.isEmpty()) {
    		for(CustomerService customerService: customerServices) {
    			customerServicesDTO.add(customerService.getCustomerServiceDTO());
    		}
    	}
    	return customerServicesDTO;
    }   
}
