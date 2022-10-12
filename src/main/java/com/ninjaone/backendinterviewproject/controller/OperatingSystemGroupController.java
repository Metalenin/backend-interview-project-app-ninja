package com.ninjaone.backendinterviewproject.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ninjaone.backendinterviewproject.model.businessClasses.OperatingSystemGroup;
import com.ninjaone.backendinterviewproject.service.crud.OperatingSystemGroupCRUDService;

@RestController
@RequestMapping("/operatingSystemGroup")
public class OperatingSystemGroupController {
    private final OperatingSystemGroupCRUDService operatingSystemGroupService;

    public OperatingSystemGroupController(OperatingSystemGroupCRUDService operatingSystemGroupService) {
        this.operatingSystemGroupService = operatingSystemGroupService;
    }

    @PostMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    private OperatingSystemGroup postOperatingSystemGroupEntity(@PathVariable String name){
        return operatingSystemGroupService.saveOperatingSystemGroupEntity(name);
    }

    @GetMapping("/{id}")
    private OperatingSystemGroup getOperatingSystemGroupEntity(@PathVariable Long id){
        return operatingSystemGroupService.getOperatingSystemGroupEntity(id)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteOperatingSystemGroupEntity(@PathVariable Long id){
        operatingSystemGroupService.deleteOperatingSystemGroupEntity(id);
    }
    
    @GetMapping("/all")
    private List<OperatingSystemGroup> getAllOperatingSystemGroupEntity(){
        return operatingSystemGroupService.getAllOperatingSystemGroupActive();
    }
}
