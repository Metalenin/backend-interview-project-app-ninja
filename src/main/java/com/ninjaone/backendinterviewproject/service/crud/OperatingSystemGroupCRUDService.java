package com.ninjaone.backendinterviewproject.service.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.OperatingSystemGroupRepository;
import com.ninjaone.backendinterviewproject.model.businessClasses.OperatingSystemGroup;
import com.ninjaone.backendinterviewproject.model.enumeration.ActiveEnum;

@Service
public class OperatingSystemGroupCRUDService {
	
    private final OperatingSystemGroupRepository operatingSystemGroupRepository;
    private static final Boolean active = ActiveEnum.YES.getValue();

    public OperatingSystemGroupCRUDService(OperatingSystemGroupRepository operatingSystemGroupRepository) {
        this.operatingSystemGroupRepository = operatingSystemGroupRepository;
    }

    public OperatingSystemGroup saveOperatingSystemGroupEntity(String name){
    	OperatingSystemGroup operatingSystemGroup = new OperatingSystemGroup(name);
        return operatingSystemGroupRepository.save(operatingSystemGroup);
    }

    public Optional<OperatingSystemGroup> getOperatingSystemGroupEntity(Long id){
        return operatingSystemGroupRepository.findByIdAndIsActive(id, active);
    }
    
    public void deleteOperatingSystemGroupEntity(Long id) {
    	Optional<OperatingSystemGroup> operatingSystemGroupOptional = operatingSystemGroupRepository.findById(id);
    	
    	if(operatingSystemGroupOptional.isPresent()) {
    		OperatingSystemGroup operatingSystemGroup = operatingSystemGroupOptional.get();
    		operatingSystemGroup.setDeleted();
    		operatingSystemGroupRepository.save(operatingSystemGroup);
    	}
    }
    
    public List<OperatingSystemGroup> getAllOperatingSystemGroupActive (){
    	List<OperatingSystemGroup> operatingSystemGroups = operatingSystemGroupRepository.findByIsActive(active);
    	if(operatingSystemGroups == null) {
    		operatingSystemGroups = new ArrayList<OperatingSystemGroup>();
    	}
    	return operatingSystemGroups;
    }
    
}
