package com.ninjaone.backendinterviewproject.service.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.ServiceCostRepository;
import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceCost;
import com.ninjaone.backendinterviewproject.model.dto.ServiceCostDTO;
import com.ninjaone.backendinterviewproject.model.enumeration.ActiveEnum;

@Service
public class ServiceCostCRUDService {
	
    private final ServiceCostRepository serviceCostRepository;
    private static final Boolean active = ActiveEnum.YES.getValue();

    public ServiceCostCRUDService(ServiceCostRepository serviceCostRepository) {
        this.serviceCostRepository = serviceCostRepository;
    }

    public ServiceCost saveServiceCostEntity(ServiceCost serviceCost){
        return serviceCostRepository.save(serviceCost);
    }

    public Optional<ServiceCost> getServiceCostEntity(Long id){
        return serviceCostRepository.findByIdAndIsActive(id, active);
    }
    
    public void deleteServiceCostEntity(Long id) {
    	Optional<ServiceCost> serviceCostOptional = serviceCostRepository.findById(id);
    	
    	if(serviceCostOptional.isPresent()) {
    		ServiceCost serviceCost = serviceCostOptional.get();
    		serviceCost.setDeleted();
    		serviceCostRepository.save(serviceCost);
    	}
    }
    
    public List<ServiceCost> getAllServiceCostActive (){
    	List<ServiceCost> serviceCosts = serviceCostRepository.findByIsActive(active);
    	if(serviceCosts == null) {
    		serviceCosts = new ArrayList<ServiceCost>();
    	}
    	return serviceCosts;
    }
    
    public List<ServiceCostDTO> getAllServiceCostActiveDTO(){
    	List<ServiceCost> serviceCosts = getAllServiceCostActive();
    	List<ServiceCostDTO> serviceCostsDTO = new ArrayList<ServiceCostDTO>();
    	if(!serviceCosts.isEmpty()) {
    		for(ServiceCost serviceCost: serviceCosts) {
    			serviceCostsDTO.add(serviceCost.getServiceCostDTO());
    		}
    	}
    	return serviceCostsDTO;
    }   
}
