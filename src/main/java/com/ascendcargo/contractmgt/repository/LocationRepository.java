package com.ascendcargo.contractmgt.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ascendcargo.contractmgt.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByCode(String code);

    boolean existsByCode(String code);
}