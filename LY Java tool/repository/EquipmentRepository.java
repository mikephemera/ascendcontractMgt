package com.ascendcargo.contractmgt.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ascendcargo.contractmgt.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Optional<Equipment> findByType(String type);
    Optional<Equipment> findByIsoCode(String isoCode);
}