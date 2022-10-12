package com.ninjaone.backendinterviewproject.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ninjaone.backendinterviewproject.model.businessClasses.Service_;
import com.ninjaone.backendinterviewproject.service.crud.ServiceCRUDService;

@RestController
@RequestMapping("/service_")
public class ServiceController {
    private final ServiceCRUDService serviceService;

    public ServiceController(ServiceCRUDService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    private Service_ postService_Entity(@PathVariable String name){
        return serviceService.saveServiceEntity(name);
    }

    @GetMapping("/{id}")
    private Service_ getService_Entity(@PathVariable Long id){
        return serviceService.getServiceEntity(id)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteService_Entity(@PathVariable Long id){
        serviceService.deleteServiceEntity(id);
    }
    
    @GetMapping("/all")
    private List<Service_> getAllService_Entity(){
        return serviceService.getAllServiceActive();
    }
}
