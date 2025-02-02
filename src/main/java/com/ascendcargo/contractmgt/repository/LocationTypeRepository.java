package com.ascendcargo.contractmgt.repository;

import com.ascendcargo.contractmgt.model.LocationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationTypeRepository extends JpaRepository<LocationType, Long> {

    boolean existsByTypeName(String typeName);

}
