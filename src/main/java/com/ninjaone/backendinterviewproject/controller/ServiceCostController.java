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

import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceCost;
import com.ninjaone.backendinterviewproject.model.dto.ServiceCostDTO;
import com.ninjaone.backendinterviewproject.service.crud.ServiceCostCRUDService;

@RestController
@RequestMapping("/serviceCost")
public class ServiceCostController {
    private final ServiceCostCRUDService serviceCostService;

    public ServiceCostController(ServiceCostCRUDService serviceCostService) {
        this.serviceCostService = serviceCostService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private ServiceCost postServiceCostEntity(@RequestBody ServiceCostDTO serviceCostDTO){
        return serviceCostService.saveServiceCostEntity(serviceCostDTO.getServiceCost());
    }

    @GetMapping("/{id}")
    private ServiceCost getServiceCostEntity(@PathVariable Long id){
        return serviceCostService.getServiceCostEntity(id)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteServiceCostEntity(@PathVariable Long id){
    	serviceCostService.deleteServiceCostEntity(id);
    }
    
    @GetMapping("/all")
    private List<ServiceCostDTO> getAllServiceCostDTO(){
        return serviceCostService.getAllServiceCostActiveDTO();
    }
}
