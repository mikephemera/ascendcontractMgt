package com.ascendcargo.contractmgt.repository;

import com.ascendcargo.contractmgt.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);

}