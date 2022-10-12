package com.ninjaone.backendinterviewproject.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ninjaone.backendinterviewproject.model.businessClasses.DeviceType;
import com.ninjaone.backendinterviewproject.service.crud.DeviceTypeCRUDService;

@RestController
@RequestMapping("/deviceType")
public class DeviceTypeController {
    private final DeviceTypeCRUDService deviceTypeService;

    public DeviceTypeController(DeviceTypeCRUDService deviceTypeService) {
        this.deviceTypeService = deviceTypeService;
    }

    @PostMapping("/{name}/{idOperatingSystemGroup}")
    @ResponseStatus(HttpStatus.CREATED)
    private DeviceType postDeviceTypeEntity(@PathVariable String name, @PathVariable Long idOperatingSystemGroup){
        return deviceTypeService.saveDeviceTypeEntity(name, idOperatingSystemGroup);
    }

    @GetMapping("/{id}")
    private DeviceType getDeviceTypeEntity(@PathVariable Long id){
        return deviceTypeService.getDeviceTypeEntity(id)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteDeviceTypeEntity(@PathVariable Long id){
        deviceTypeService.deleteDeviceTypeEntity(id);
    }
    
    @GetMapping("/all")
    private List<DeviceType> getAllDeviceTypeEntity(){
        return deviceTypeService.getAllDeviceTypeActive();
    }
}
