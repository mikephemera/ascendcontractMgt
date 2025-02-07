package com.ascendcargo.contractmgt.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ascendcargo.contractmgt.model.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Optional<Contract> findByContractReference(String contractReference);
    List<Contract> findByStatus(Contract.ContractStatus status);
    boolean existsByContractReference(String ref);
}