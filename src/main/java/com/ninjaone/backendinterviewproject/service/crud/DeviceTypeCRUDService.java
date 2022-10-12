package com.ninjaone.backendinterviewproject.service.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.DeviceTypeRepository;
import com.ninjaone.backendinterviewproject.model.businessClasses.DeviceType;
import com.ninjaone.backendinterviewproject.model.businessClasses.OperatingSystemGroup;
import com.ninjaone.backendinterviewproject.model.enumeration.ActiveEnum;


@Service
public class DeviceTypeCRUDService {
	
    private final DeviceTypeRepository deviceTypeRepository;
    private static final Boolean active = ActiveEnum.YES.getValue();
    
    @Autowired
    private  OperatingSystemGroupCRUDService  operatingSystemGroupCRUDService;

    public DeviceTypeCRUDService(DeviceTypeRepository deviceTypeRepository) {
        this.deviceTypeRepository = deviceTypeRepository;
    }

    public DeviceType saveDeviceTypeEntity(String name, Long idOperatingSystemGroup){
    	
    	Optional<OperatingSystemGroup> operatingSystemGroupOptional = operatingSystemGroupCRUDService.getOperatingSystemGroupEntity(idOperatingSystemGroup);
    	if(operatingSystemGroupOptional.isPresent()) {
    		OperatingSystemGroup operatingSystemGroup = operatingSystemGroupOptional.get();
    		DeviceType deviceType = new DeviceType(name, operatingSystemGroup);
            return deviceTypeRepository.save(deviceType);
    	} else {
    		return new DeviceType();
    	}
    }

    public Optional<DeviceType> getDeviceTypeEntity(Long id){
        return deviceTypeRepository.findById(id);
    }
    
    public void deleteDeviceTypeEntity(Long id) {
    	Optional<DeviceType> deviceTypeOptional = deviceTypeRepository.findById(id);
    	
    	if(deviceTypeOptional.isPresent()) {
    		DeviceType deviceType = deviceTypeOptional.get();
    		deviceType.setDeleted();
    		deviceTypeRepository.save(deviceType);
    	}
    }
    
    public List<DeviceType> getAllDeviceTypeActive (){
    	List<DeviceType> deviceTypes = deviceTypeRepository.findByIsActive(active);
    	if(deviceTypes == null) {
    		deviceTypes = new ArrayList<DeviceType>();
    	}
    	return deviceTypes;
    }
    
}
