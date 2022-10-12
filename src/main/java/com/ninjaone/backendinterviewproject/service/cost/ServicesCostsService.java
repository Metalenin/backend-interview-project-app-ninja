package com.ninjaone.backendinterviewproject.service.cost;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.ServiceCostRepository;
import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceCost;
import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceOperatingSystemGroupCost;
import com.ninjaone.backendinterviewproject.model.enumeration.ActiveEnum;

@Service
public class ServicesCostsService {
	
	private final ServiceCostRepository serviceCostRepository;
    private static final Boolean active = ActiveEnum.YES.getValue();
    
    @Autowired
    CustomerServicesService customerServicesService;

    public ServicesCostsService(ServiceCostRepository serviceCostRepository) {
        this.serviceCostRepository = serviceCostRepository;
    }
    
    public List<ServiceCost> getServiceCostsFromListIdServices (List<Long> idServices){
 	   List<ServiceCost> serviceCosts = new ArrayList<ServiceCost>();
 	   
 	   serviceCosts = this.serviceCostRepository.findByListIdServiceAndIsActive(idServices, active);
 	   
 	   return serviceCosts;
    }
    
    public List<ServiceCost> getServiceCostsFromIdCustomer (Long idCustomer){
    	
    	List<Long> idServices = this.customerServicesService.getListServiceIdFromIdCustomer(idCustomer) ;
    	List<ServiceCost> serviceCosts = this.getServiceCostsFromListIdServices(idServices);
    	
  	   return serviceCosts;
    }
    
    public List<ServiceCost> filterServiceCostsWithoutOSFromList (List<ServiceCost> serviceCosts){
    	
    	List<ServiceCost> serviceCostsWithoutOS = serviceCosts
    			.stream()
    			.filter(sc -> !(sc instanceof ServiceOperatingSystemGroupCost))
    			.collect(Collectors.toList());
    	
  	   return serviceCostsWithoutOS;
    }
    
    public List<ServiceOperatingSystemGroupCost> filterServiceCostsWithOSFromList (List<ServiceCost> serviceCosts){
    	
    	List<ServiceOperatingSystemGroupCost> serviceCostsWithtOS = serviceCosts
    			.stream()
    			.filter(ServiceOperatingSystemGroupCost.class::isInstance)
    			.map (ServiceOperatingSystemGroupCost.class::cast)
    			.collect(Collectors.toList());
    	
  	   return serviceCostsWithtOS;
    }
    
    
}
