package com.ascendcargo.contractmgt.repository;

import com.ascendcargo.contractmgt.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}