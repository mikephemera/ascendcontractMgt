package com.ascendcargo.contractmgt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ascendcargo.contractmgt.model.Lane;

public interface LaneRepository extends JpaRepository<Lane, Long> {
    List<Lane> findByContract_Id(Long contractId);

    List<Lane> findByContractId(Long contractId);
}