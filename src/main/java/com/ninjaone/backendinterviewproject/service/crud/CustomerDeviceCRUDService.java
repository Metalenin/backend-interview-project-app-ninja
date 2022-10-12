package com.ninjaone.backendinterviewproject.service.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.CustomerDeviceRepository;
import com.ninjaone.backendinterviewproject.model.businessClasses.CustomerDevice;
import com.ninjaone.backendinterviewproject.model.dto.CustomerDeviceDTO;
import com.ninjaone.backendinterviewproject.model.enumeration.ActiveEnum;

@Service
public class CustomerDeviceCRUDService {
	
    private final CustomerDeviceRepository customerDeviceRepository;
    private static final Boolean active = ActiveEnum.YES.getValue();

    public CustomerDeviceCRUDService(CustomerDeviceRepository customerDeviceRepository) {
        this.customerDeviceRepository = customerDeviceRepository;
    }

    public CustomerDevice saveCustomerDeviceEntity(CustomerDevice customerDevice){
        return customerDeviceRepository.save(customerDevice);
    }

    public Optional<CustomerDevice> getCustomerDeviceEntity(Long id){
        return customerDeviceRepository.findByIdAndIsActive(id, active);
    }
    
    public void deleteCustomerDeviceEntity(Long id) {
    	Optional<CustomerDevice> customerDeviceOptional = customerDeviceRepository.findById(id);
    	
    	if(customerDeviceOptional.isPresent()) {
    		CustomerDevice customerDevice = customerDeviceOptional.get();
    		customerDevice.setDeleted();
    		customerDeviceRepository.save(customerDevice);
    	}
    }
    
    public List<CustomerDevice> getAllCustomerDeviceActive (){
    	List<CustomerDevice> customerDevices = customerDeviceRepository.findByIsActive(active);
    	if(customerDevices == null) {
    		customerDevices = new ArrayList<CustomerDevice>();
    	}
    	return customerDevices;
    }
    
    public List<CustomerDeviceDTO> getAllCustomerDeviceActiveDTO(){
    	List<CustomerDevice> customerDevices = getAllCustomerDeviceActive();
    	List<CustomerDeviceDTO> customerDevicesDTO = new ArrayList<CustomerDeviceDTO>();
    	if(!customerDevicesDTO.isEmpty()) {
    		for(CustomerDevice customerDevice: customerDevices) {
    			customerDevicesDTO.add(customerDevice.getCustomerDeviceDTO());
    		}
    	}
    	return customerDevicesDTO;
    }   
}
