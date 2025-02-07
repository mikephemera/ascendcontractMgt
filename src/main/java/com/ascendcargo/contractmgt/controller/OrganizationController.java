package com.ascendcargo.contractmgt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ascendcargo.contractmgt.model.Organization;
import com.ascendcargo.contractmgt.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<Organization> createOrganization(@Valid @RequestBody Organization organization) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.createOrganization(organization));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organization> getOrganization(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.getOrganization(id));
    }

    @PutMapping("/{id}/legal-name")
    public ResponseEntity<Organization> updateLegalName(
            @PathVariable Long id,
            @RequestParam String newLegalName
    ) {
        return ResponseEntity.ok(organizationService.updateLegalName(id, newLegalName));
    }
}