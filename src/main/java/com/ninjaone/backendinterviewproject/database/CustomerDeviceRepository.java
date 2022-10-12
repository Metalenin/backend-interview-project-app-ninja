package com.ninjaone.backendinterviewproject.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.businessClasses.CustomerDevice;

@Repository
public interface CustomerDeviceRepository extends CrudRepository<CustomerDevice, Long> {
	
	Optional<CustomerDevice> findByIdAndIsActive(Long id, Boolean isActive);
	
	List<CustomerDevice> findByIsActive(Boolean isActive);
	
	@Query("FROM CustomerDevice cd "
			+" WHERE cd.isActive = :isActive"
			+" AND cd.customer.isActive = :isActive"
			+" AND cd.device.isActive = :isActive"
			+" AND cd.customer.id = :idCustomer"
			)
	List<CustomerDevice> findByIdCustomerAndIsActive(@Param("idCustomer")Long idCustomer, @Param("isActive")Boolean isActive);
	
	@Query( "SELECT count(cd) "
			+ "FROM CustomerDevice cd "
			+" WHERE cd.isActive = :isActive"
			+" AND cd.customer.isActive = :isActive"
			+" AND cd.device.isActive = :isActive"
			+" AND cd.customer.id = :idCustomer"
			)
	Long countTotalActiveDevicePerCustomerQuery(@Param("idCustomer")Long idCustomer, @Param("isActive")Boolean isActive);
	
	@Query( "SELECT count(cd) "
			+ "FROM CustomerDevice cd "
			+" WHERE cd.isActive = :isActive"
			+" AND cd.customer.isActive = :isActive"
			+" AND cd.device.isActive = :isActive"
			+" AND cd.customer.id = :idCustomer"
			+" AND cd.device.deviceType.operatingSystemGroup.id = :idOperatingSystemGroup"
			)
	Long countTotalActiveDevicePerCustomerAndOSGroupQuery(@Param("idCustomer")Long idCustomer, @Param("idOperatingSystemGroup")Long idOperatingSystemGroup,
			@Param("isActive")Boolean isActive);
	
	@Query( "SELECT distinct(cd.device.deviceType.operatingSystemGroup.id) "
			+ "FROM CustomerDevice cd "
			+" WHERE cd.isActive = :isActive"
			+" AND cd.customer.isActive = :isActive"
			+" AND cd.device.isActive = :isActive"
			+" AND cd.customer.id = :idCustomer"
			)
	List<Long> getListDistinctOSGroupAndIdCustomerAndIsActive(@Param("idCustomer")Long idCustomer, @Param("isActive")Boolean isActive);
	
}

	
