package com.ascendcargo.contractmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ascendcargo.contractmgt.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    boolean existsByTaxId(String taxId);
}