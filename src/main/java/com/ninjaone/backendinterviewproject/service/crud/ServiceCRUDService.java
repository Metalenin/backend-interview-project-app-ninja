package com.ninjaone.backendinterviewproject.service.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.ServiceRepository;
import com.ninjaone.backendinterviewproject.model.businessClasses.Service_;
import com.ninjaone.backendinterviewproject.model.enumeration.ActiveEnum;

@Service
public class ServiceCRUDService {
	
    private final ServiceRepository serviceRepository;
    private static final Boolean active = ActiveEnum.YES.getValue();

    public ServiceCRUDService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Service_ saveServiceEntity(String name){
    	Service_ service_ = new Service_(name);
        return serviceRepository.save(service_);
    }

    public Optional<Service_> getServiceEntity(Long id){
        return serviceRepository.findByIdAndIsActive(id, active);
    }
    
    public void deleteServiceEntity(Long id) {
    	Optional<Service_> serviceOptional = serviceRepository.findById(id);
    	
    	if(serviceOptional.isPresent()) {
    		Service_ service_ = serviceOptional.get();
    		service_.setDeleted();
    		serviceRepository.save(service_);
    	}
    }
    
    public List<Service_> getAllServiceActive (){
    	List<Service_> services = serviceRepository.findByIsActive(active);
    	if(services == null) {
    		services = new ArrayList<Service_>();
    	}
    	return services;
    }
    
}
