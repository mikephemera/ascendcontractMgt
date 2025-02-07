package com.ascendcargo.contractmgt.service;

import com.ascendcargo.contractmgt.dto.OrganizationDTO;
import com.ascendcargo.contractmgt.model.Organization;

import java.util.List;

public interface OrganizationService {
    Organization createOrganization(OrganizationDTO dto);
    OrganizationDTO updateOrganization(Long id, OrganizationDTO dto);
    List<OrganizationDTO> getAllOrganizations();
    Organization getOrganization(Long id);
    void deleteOrganization(Long id);

}