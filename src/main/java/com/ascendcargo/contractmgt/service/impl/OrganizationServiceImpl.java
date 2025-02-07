package com.ascendcargo.contractmgt.service.impl;

import com.ascendcargo.contractmgt.dto.OrganizationDTO;
import com.ascendcargo.contractmgt.model.Location;
import com.ascendcargo.contractmgt.model.Organization;
import com.ascendcargo.contractmgt.repository.LocationRepository;
import com.ascendcargo.contractmgt.repository.OrganizationRepository;
import com.ascendcargo.contractmgt.service.OrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private OrganizationRepository organizationRepository;

    public Organization createOrganization(OrganizationDTO dto) {
        Organization org = new Organization();
        BeanUtils.copyProperties(dto, org);

        // if (dto.getLocationId() != null) {
        // Location location = locationRepository.findById(dto.getLocationId())
        // .orElseThrow(() -> new CustomException("Location not found"));
        // org.setLocation( location);
        // }

        org = organizationRepository.save(org);
        // BeanUtils.copyProperties(org, dto);
        return org;

    }

    public OrganizationDTO updateOrganization(Long id, OrganizationDTO dto) {
        Organization org = organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
        BeanUtils.copyProperties(dto, org, "uniqueID");

        org = organizationRepository.save(org);
        BeanUtils.copyProperties(org, dto);
        return dto;
    }

    public List<OrganizationDTO> getAllOrganizations() {
        return organizationRepository.findAll().stream().map(org -> {
            OrganizationDTO dto = new OrganizationDTO();
            BeanUtils.copyProperties(org, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    // public Organization getOrganization(Long id) {
    // Organization org = organizationRepository.findById(id)
    // .orElseThrow(() -> new CustomException("BR14000", "en"));
    // OrganizationDTO dto = new OrganizationDTO();
    // BeanUtils.copyProperties(org, dto);
    // return org;
    // }

    public void deleteOrganization(Long id) {}

    // if (!organizationRepository.existsById(id)) {
    // throw new CustomException("BR14000", "en");
    public Organization getOrganization(Long id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
    }
}
