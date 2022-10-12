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

import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceOperatingSystemGroupCost;
import com.ninjaone.backendinterviewproject.model.dto.ServiceOperatingSystemGroupCostDTO;
import com.ninjaone.backendinterviewproject.service.crud.ServiceOperatingSystemGroupCostCRUDService;

@RestController
@RequestMapping("/serviceOperatingSystemGroupCost")
public class ServiceOperatingSystemGroupCostController {
    private final ServiceOperatingSystemGroupCostCRUDService serviceOperatingSystemGroupCostService;

    public ServiceOperatingSystemGroupCostController(ServiceOperatingSystemGroupCostCRUDService serviceOperatingSystemGroupCostService) {
        this.serviceOperatingSystemGroupCostService = serviceOperatingSystemGroupCostService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private ServiceOperatingSystemGroupCost postServiceOperatingSystemGroupCostEntity(@RequestBody ServiceOperatingSystemGroupCostDTO serviceOperatingSystemGroupCostDTO){
        return serviceOperatingSystemGroupCostService.saveServiceOperatingSystemGroupCostEntity(serviceOperatingSystemGroupCostDTO.getServiceOperatingSystemGroupCost());
    }

    @GetMapping("/{id}")
    private ServiceOperatingSystemGroupCost getServiceOperatingSystemGroupCostEntity(@PathVariable Long id){
        return serviceOperatingSystemGroupCostService.getServiceOperatingSystemGroupCostEntity(id)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteServiceOperatingSystemGroupCostEntity(@PathVariable Long id){
    	serviceOperatingSystemGroupCostService.deleteServiceOperatingSystemGroupCostEntity(id);
    }
    
    @GetMapping("/all")
    private List<ServiceOperatingSystemGroupCostDTO> getAllServiceOperatingSystemGroupCostDTO(){
        return serviceOperatingSystemGroupCostService.getAllServiceOperatingSystemGroupCostActiveDTO();
    }
}
