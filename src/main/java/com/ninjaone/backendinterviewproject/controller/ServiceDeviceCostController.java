package com.ninjaone.backendinterviewproject.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceDeviceCost;
import com.ninjaone.backendinterviewproject.model.dto.ServiceDeviceCostDTO;
import com.ninjaone.backendinterviewproject.service.crud.ServiceDeviceCostCRUDService;

@RestController
@RequestMapping("/serviceDeviceCost")
public class ServiceDeviceCostController {
    private final ServiceDeviceCostCRUDService serviceDeviceCostService;

    public ServiceDeviceCostController(ServiceDeviceCostCRUDService serviceDeviceCostService) {
        this.serviceDeviceCostService = serviceDeviceCostService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private ServiceDeviceCost postServiceDeviceCostEntity(@RequestBody ServiceDeviceCostDTO serviceDeviceCostDTO){
        return serviceDeviceCostService.saveServiceDeviceCostEntity(serviceDeviceCostDTO.getServiceDeviceCost());
    }

    @GetMapping("/{id}")
    private ServiceDeviceCost getServiceDeviceCostEntity(@PathVariable Long id){
        return serviceDeviceCostService.getServiceDeviceCostEntity(id)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteServiceDeviceCostEntity(@PathVariable Long id){
    	serviceDeviceCostService.deleteServiceDeviceCostEntity(id);
    }
    
    @GetMapping("/all")
    private List<ServiceDeviceCostDTO> getAllServiceDeviceCostDTO(){
        return serviceDeviceCostService.getAllServiceDeviceCostActiveDTO();
    }
}
