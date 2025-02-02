package com.ascendcargo.contractmgt.repository;

import com.ascendcargo.contractmgt.model.Rate;
import com.ascendcargo.contractmgt.model.Rate.ContractType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Long> {

    List<Rate> findAllByContractType(ContractType type);
}