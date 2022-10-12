package com.ninjaone.backendinterviewproject.service.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.ServiceOperatingSystemGroupCostRepository;
import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceOperatingSystemGroupCost;
import com.ninjaone.backendinterviewproject.model.dto.ServiceOperatingSystemGroupCostDTO;
import com.ninjaone.backendinterviewproject.model.enumeration.ActiveEnum;

@Service
public class ServiceOperatingSystemGroupCostCRUDService {
	
    private final ServiceOperatingSystemGroupCostRepository serviceOperatingSystemGroupCostRepository;
    private static final Boolean active = ActiveEnum.YES.getValue();

    public ServiceOperatingSystemGroupCostCRUDService(ServiceOperatingSystemGroupCostRepository serviceOperatingSystemGroupCostRepository) {
        this.serviceOperatingSystemGroupCostRepository = serviceOperatingSystemGroupCostRepository;
    }

    public ServiceOperatingSystemGroupCost saveServiceOperatingSystemGroupCostEntity(ServiceOperatingSystemGroupCost serviceOperatingSystemGroupCost){
        return serviceOperatingSystemGroupCostRepository.save(serviceOperatingSystemGroupCost);
    }

    public Optional<ServiceOperatingSystemGroupCost> getServiceOperatingSystemGroupCostEntity(Long id){
        return serviceOperatingSystemGroupCostRepository.findByIdAndIsActive(id, active);
    }
    
    public void deleteServiceOperatingSystemGroupCostEntity(Long id) {
    	Optional<ServiceOperatingSystemGroupCost> serviceOperatingSystemGroupCostOptional = serviceOperatingSystemGroupCostRepository.findById(id);
    	
    	if(serviceOperatingSystemGroupCostOptional.isPresent()) {
    		ServiceOperatingSystemGroupCost serviceOperatingSystemGroupCost = serviceOperatingSystemGroupCostOptional.get();
    		serviceOperatingSystemGroupCost.setDeleted();
    		serviceOperatingSystemGroupCostRepository.save(serviceOperatingSystemGroupCost);
    	}
    }
    
    public List<ServiceOperatingSystemGroupCost> getAllServiceOperatingSystemGroupCostActive (){
    	List<ServiceOperatingSystemGroupCost> serviceOperatingSystemGroupCosts = serviceOperatingSystemGroupCostRepository.findByIsActive(active);
    	if(serviceOperatingSystemGroupCosts == null) {
    		serviceOperatingSystemGroupCosts = new ArrayList<ServiceOperatingSystemGroupCost>();
    	}
    	return serviceOperatingSystemGroupCosts;
    }
    
    public List<ServiceOperatingSystemGroupCostDTO> getAllServiceOperatingSystemGroupCostActiveDTO(){
    	List<ServiceOperatingSystemGroupCost> serviceOperatingSystemGroupCosts = getAllServiceOperatingSystemGroupCostActive();
    	List<ServiceOperatingSystemGroupCostDTO> serviceOperatingSystemGroupCostsDTO = new ArrayList<ServiceOperatingSystemGroupCostDTO>();
    	if(!serviceOperatingSystemGroupCostsDTO.isEmpty()) {
    		for(ServiceOperatingSystemGroupCost serviceOperatingSystemGroupCost: serviceOperatingSystemGroupCosts) {
    			serviceOperatingSystemGroupCostsDTO.add(serviceOperatingSystemGroupCost.getServiceOperatingSystemGroupCostDTO());
    		}
    	}
    	return serviceOperatingSystemGroupCostsDTO;
    }  
}
