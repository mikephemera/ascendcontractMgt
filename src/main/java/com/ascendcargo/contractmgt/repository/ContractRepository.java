package com.ascendcargo.contractmgt.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ascendcargo.contractmgt.model.Contract;
import com.ascendcargo.contractmgt.model.Contract.ContractStatus;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Optional<Contract> findByContractReference(String contractReference);

    List<Contract> findByStatus(Contract.ContractStatus status);

    boolean existsByContractReference(String ref);

    @Query("SELECT c FROM Contract c WHERE " + "(:status IS NULL OR c.status = :status) AND "
            + "(:startDate IS NULL OR c.effectiveDate >= :startDate) AND "
            + "(:endDate IS NULL OR c.expirationDate <= :endDate)")
    List<Contract> searchContracts(@Param("status") ContractStatus status,
            @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
