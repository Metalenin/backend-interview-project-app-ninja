package com.ninjaone.backendinterviewproject.service.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.model.businessClasses.Device;
import com.ninjaone.backendinterviewproject.model.businessClasses.DeviceType;
import com.ninjaone.backendinterviewproject.model.enumeration.ActiveEnum;


@Service
public class DeviceCRUDService {
	
    private final DeviceRepository deviceRepository;
    private static final Boolean active = ActiveEnum.YES.getValue();
    
    @Autowired
    private  DeviceTypeCRUDService  deviceTypeCRUDService;

    public DeviceCRUDService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device saveDeviceEntity(String systemName, Long idDeviceType){
    	
    	Optional<DeviceType> deviceTypeOptional = deviceTypeCRUDService.getDeviceTypeEntity(idDeviceType);
    	if(deviceTypeOptional.isPresent()) {
    		DeviceType deviceType = deviceTypeOptional.get();
    		Device device = new Device(systemName, deviceType);
            return deviceRepository.save(device);
    	} else {
    		return new Device();
    	}
    }

    public Optional<Device> getDeviceEntity(Long id){
        return deviceRepository.findById(id);
    }
    
    public void deleteDeviceEntity(Long id) {
    	Optional<Device> deviceOptional = deviceRepository.findById(id);
    	
    	if(deviceOptional.isPresent()) {
    		Device device = deviceOptional.get();
    		device.setDeleted();
    		deviceRepository.save(device);
    	}
    }
    
    public List<Device> getAllDeviceActive (){
    	List<Device> devices = deviceRepository.findByIsActive(active);
    	if(devices == null) {
    		devices = new ArrayList<Device>();
    	}
    	return devices;
    }
    
}
