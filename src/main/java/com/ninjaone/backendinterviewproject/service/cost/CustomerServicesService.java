package com.ninjaone.backendinterviewproject.service.cost;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.CustomerServiceRepository;
import com.ninjaone.backendinterviewproject.model.businessClasses.CustomerService;
import com.ninjaone.backendinterviewproject.model.enumeration.ActiveEnum;

@Service
public class CustomerServicesService {
	
	private final CustomerServiceRepository customerServiceRepository;
    private static final Boolean active = ActiveEnum.YES.getValue();


    public CustomerServicesService(CustomerServiceRepository customerServiceRepository) {
        this.customerServiceRepository = customerServiceRepository;
    }

   public List<CustomerService> getCustomerServices (Long idCustomer){
	   List<CustomerService> customerServices = new ArrayList<CustomerService>();	   
	   customerServices = this.customerServiceRepository.findByIdCustomerAndIsActive(idCustomer, active);	   
	   return customerServices;
   }
   
   public List<Long> getListServiceIdFromListCustomerService(List<CustomerService> customerServices){
	   List<Long> idsService = new ArrayList<Long>();
	   if(customerServices!= null && !customerServices.isEmpty()) {
		   idsService = customerServices
				   .stream()
				   .map(cs -> cs.getService_().getId())
				   .collect(Collectors.toList());  
	   }
	   return idsService;
   }
   
   public List<Long> getListServiceIdFromIdCustomer(Long idCustomer){
	   
	   List<CustomerService> customerServices = getCustomerServices(idCustomer);
	   List<Long> idServices = getListServiceIdFromListCustomerService(customerServices);
	   
	   return idServices;
   }
    
}
