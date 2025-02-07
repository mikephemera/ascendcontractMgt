package com.ascendcargo.contractmgt.service;

import java.time.LocalDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ascendcargo.contractmgt.model.Contract;
import com.ascendcargo.contractmgt.repository.ContractRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    private final OrganizationService organizationService;
    private final LaneService laneService;

    public Contract createContract(Contract contract) {
        validateContractDates(contract);
        validateContractReference(contract.getContractReference());
        
        // 确保关联组织存在
        organizationService.getOrganization(contract.getOrganization().getId());
        
        Contract savedContract = contractRepository.save(contract);
        
        // 级联处理运输通道
        if (contract.getLanes() != null) {
            contract.getLanes().forEach(lane -> {
                lane.setContract(savedContract);
                laneService.createLane(lane);
            });
        }
        return savedContract;
    }

    public Contract activateContract(Long contractId) {
        Contract contract = getContract(contractId);
        if (contract.getEffectiveDate().isAfter(LocalDate.now())) {
            throw new IllegalStateException("Cannot activate contract before effective date");
        }
        contract.setStatus(Contract.ContractStatus.ACTIVE);
        return contractRepository.save(contract);
    }

    @Transactional(readOnly = true)
    public Contract getContract(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contract not found"));
    }

    private void validateContractDates(Contract contract) {
        if (contract.getEffectiveDate().isAfter(contract.getExpirationDate())) {
            throw new IllegalArgumentException("Effective date cannot be after expiration date");
        }
    }

    private void validateContractReference(String ref) {
        if (ref != null && contractRepository.existsByContractReference(ref)) {
            throw new IllegalStateException("Contract reference must be unique");
        }
    }

    public Contract findByContractReference(String contractRef) {
        return contractRepository.findByContractReference(contractRef)
                .orElseThrow(() -> new EntityNotFoundException("Contract not found with reference: " + contractRef));
    }
}