package com.ascendcargo.contractmgt.repository;

import com.ascendcargo.contractmgt.model.Location;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByLocationType_TypeName(String typeName);
}
