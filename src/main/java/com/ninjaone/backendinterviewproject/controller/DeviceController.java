package com.ninjaone.backendinterviewproject.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ninjaone.backendinterviewproject.model.businessClasses.Device;
import com.ninjaone.backendinterviewproject.service.crud.DeviceCRUDService;

@RestController
@RequestMapping("/device")
public class DeviceController {
    private final DeviceCRUDService deviceService;

    public DeviceController(DeviceCRUDService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/{name}/{idOperatingSystemGroup}")
    @ResponseStatus(HttpStatus.CREATED)
    private Device postDeviceEntity(@PathVariable String name, @PathVariable Long idOperatingSystemGroup){
        return deviceService.saveDeviceEntity(name, idOperatingSystemGroup);
    }

    @GetMapping("/{id}")
    private Device getDeviceEntity(@PathVariable Long id){
        return deviceService.getDeviceEntity(id)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteDeviceEntity(@PathVariable Long id){
        deviceService.deleteDeviceEntity(id);
    }
    
    @GetMapping("/all")
    private List<Device> getAllDeviceEntity(){
        return deviceService.getAllDeviceActive();
    }
}
