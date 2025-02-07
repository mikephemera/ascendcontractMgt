package com.ascendcargo.contractmgt.repository;

import com.ascendcargo.contractmgt.model.Location;
//import com.ascendcargo.contractmgt.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Set<Location> findByIdIn(Set<Long> locationIds);
}