package com.ascendcargo.contractmgt.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ascendcargo.contractmgt.model.Contract;
import com.ascendcargo.contractmgt.model.Lane;
import com.ascendcargo.contractmgt.service.ContractService;
import com.ascendcargo.contractmgt.service.LaneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;
    private final LaneService laneService;

    @PostMapping
    public ResponseEntity<Contract> createContract(@Valid @RequestBody Contract contract) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contractService.createContract(contract));
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<Contract> activateContract(@PathVariable Long id) {
        return ResponseEntity.ok(contractService.activateContract(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContract(@PathVariable Long id) {
        return ResponseEntity.ok(contractService.getContract(id));
    }

    @GetMapping("/ref/{contractRef}")
    public ResponseEntity<Contract> getByReference(@PathVariable String contractRef) {
        return ResponseEntity.ok(contractService.findByContractReference(contractRef));
    }

    @PostMapping("/{contractId}/lanes")
    public ResponseEntity<Lane> createLane(@PathVariable Long contractId,
            @Valid @RequestBody Lane lane) {
        Contract contract = contractService.getContract(contractId);
        lane.setContract(contract);
        return ResponseEntity.status(HttpStatus.CREATED).body(laneService.createLane(lane));
    }

    @GetMapping("/{contractId}/lanes")
    public ResponseEntity<List<Lane>> getContractLanes(@PathVariable Long contractId) {
        return ResponseEntity.ok(laneService.getLanesByContractId(contractId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable Long id,
            @Valid @RequestBody Contract contractDetails) {
        Contract updatedContract = contractService.updateContract(id, contractDetails);
        return ResponseEntity.ok(updatedContract);
    }
}
