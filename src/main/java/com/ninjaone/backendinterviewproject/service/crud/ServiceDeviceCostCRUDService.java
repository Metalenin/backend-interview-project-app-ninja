package com.ninjaone.backendinterviewproject.service.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.ServiceDeviceCostRepository;
import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceDeviceCost;
import com.ninjaone.backendinterviewproject.model.dto.ServiceDeviceCostDTO;
import com.ninjaone.backendinterviewproject.model.enumeration.ActiveEnum;

@Service
public class ServiceDeviceCostCRUDService {
	
    private final ServiceDeviceCostRepository serviceDeviceCostRepository;
    private static final Boolean active = ActiveEnum.YES.getValue();

    public ServiceDeviceCostCRUDService(ServiceDeviceCostRepository serviceDeviceCostRepository) {
        this.serviceDeviceCostRepository = serviceDeviceCostRepository;
    }

    public ServiceDeviceCost saveServiceDeviceCostEntity(ServiceDeviceCost serviceDeviceCost){
        return serviceDeviceCostRepository.save(serviceDeviceCost);
    }

    public Optional<ServiceDeviceCost> getServiceDeviceCostEntity(Long id){
        return serviceDeviceCostRepository.findByIdAndIsActive(id, active);
    }
    
    public void deleteServiceDeviceCostEntity(Long id) {
    	Optional<ServiceDeviceCost> serviceDeviceCostOptional = serviceDeviceCostRepository.findById(id);
    	
    	if(serviceDeviceCostOptional.isPresent()) {
    		ServiceDeviceCost serviceDeviceCost = serviceDeviceCostOptional.get();
    		serviceDeviceCost.setDeleted();
    		serviceDeviceCostRepository.save(serviceDeviceCost);
    	}
    }
    
    public List<ServiceDeviceCost> getAllServiceDeviceCostActive (){
    	List<ServiceDeviceCost> serviceDeviceCosts = serviceDeviceCostRepository.findByIsActive(active);
    	if(serviceDeviceCosts == null) {
    		serviceDeviceCosts = new ArrayList<ServiceDeviceCost>();
    	}
    	return serviceDeviceCosts;
    }
    
    public List<ServiceDeviceCostDTO> getAllServiceDeviceCostActiveDTO(){
    	List<ServiceDeviceCost> serviceDeviceCosts = getAllServiceDeviceCostActive();
    	List<ServiceDeviceCostDTO> serviceDeviceCostsDTO = new ArrayList<ServiceDeviceCostDTO>();
    	if(!serviceDeviceCosts.isEmpty()) {
    		for(ServiceDeviceCost serviceDeviceCost: serviceDeviceCosts) {
    			serviceDeviceCostsDTO.add(serviceDeviceCost.getServiceDeviceCostDTO());
    		}
    	}
    	return serviceDeviceCostsDTO;
    } 
    
    public ServiceDeviceCost getServiceDeviceCostGeneralEntity(){
    	Optional<ServiceDeviceCost> optionalServiceDeviceCost = serviceDeviceCostRepository.findByIsActiveDeviceTypeNull(active);
    	ServiceDeviceCost serviceDeviceCost = new ServiceDeviceCost();
    	if(optionalServiceDeviceCost.isPresent()) {
    		serviceDeviceCost = optionalServiceDeviceCost.get();
    	}
        return serviceDeviceCost;
    }
}
