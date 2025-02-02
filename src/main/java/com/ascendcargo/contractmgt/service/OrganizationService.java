package com.ascendcargo.contractmgt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ascendcargo.contractmgt.model.Organization;
import com.ascendcargo.contractmgt.repository.OrganizationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    public Organization createOrganization(Organization organization) {
        if (organizationRepository.existsByTaxId(organization.getTaxId())) {
            throw new IllegalStateException("Tax ID already exists");
        }
        return organizationRepository.save(organization);
    }

    @Transactional(readOnly = true)
    public Organization getOrganization(Long id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));
    }

    public Organization updateLegalName(Long id, String newLegalName) {
        Organization org = getOrganization(id);
        org.setLegalName(newLegalName);
        return organizationRepository.save(org);
    }
}