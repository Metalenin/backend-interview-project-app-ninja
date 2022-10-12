package com.ninjaone.backendinterviewproject.service.cost;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.CustomerDeviceRepository;
import com.ninjaone.backendinterviewproject.model.enumeration.ActiveEnum;

@Service
public class CustomerDeviceCountService {
	private final CustomerDeviceRepository customerDeviceRepository;
    private static final Boolean active = ActiveEnum.YES.getValue();


    public CustomerDeviceCountService(CustomerDeviceRepository customerDeviceRepository) {
        this.customerDeviceRepository = customerDeviceRepository;
    }

    public Long countTotalActiveDevicePerCustomer (Long idCustomer) {
    	Long totalActiveDevicePerCustomer = (long) 0;
    	totalActiveDevicePerCustomer = customerDeviceRepository.countTotalActiveDevicePerCustomerQuery(idCustomer, active);
    	return totalActiveDevicePerCustomer;
    }
    
    public Long countTotalActiveDevicePerCustomerAndOperatingSystemGroup (Long idCustomer, Long idOperatingSystemGroup) {
    	Long totalActiveDevicePerCustomerAndOS = (long) 0;
    	totalActiveDevicePerCustomerAndOS = customerDeviceRepository.countTotalActiveDevicePerCustomerAndOSGroupQuery(idCustomer, idOperatingSystemGroup, active);
    	return totalActiveDevicePerCustomerAndOS;
    }
    
    public HashMap<Long, Long> mapCountTotalActiveDevicePerCustomerAndOperatingSystemGroupList(Long idCustomer, List<Long>idOperatingSystemGroups){
    	HashMap<Long, Long> customerNumberOfDevicesPerOS = new HashMap<Long, Long>();
        if(idOperatingSystemGroups!= null && !idOperatingSystemGroups.isEmpty()) {
        	for(Long idOperatingSystemGroup: idOperatingSystemGroups) {
        		Long totalActiveDevicePerCustomerAndOS = this.countTotalActiveDevicePerCustomerAndOperatingSystemGroup(idCustomer, idOperatingSystemGroup);
        		customerNumberOfDevicesPerOS.put(idOperatingSystemGroup, totalActiveDevicePerCustomerAndOS);
        	}
        }
        return customerNumberOfDevicesPerOS;
    }
    
    public List<Long> getListAllSOGroupIdPerCustomer (Long idCustomer) {
    	List<Long> allSOGroupIdPerCustomer = customerDeviceRepository.getListDistinctOSGroupAndIdCustomerAndIsActive(idCustomer, active);
    	return allSOGroupIdPerCustomer;
    }	
   
 
}
