package com.ascendcargo.contractmgt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ascendcargo.contractmgt.model.ContractAccessorial;

public interface ContractAccessorialRepository extends JpaRepository<ContractAccessorial, Long> {
    List<ContractAccessorial> findByContract_IdAndType(Long contractId, ContractAccessorial.Type type);
}