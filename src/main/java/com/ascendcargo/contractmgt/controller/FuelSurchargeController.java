package com.ascendcargo.contractmgt.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ascendcargo.contractmgt.model.FuelSurcharge;
import com.ascendcargo.contractmgt.service.FuelSurchargeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/fuel-surcharges")
@RequiredArgsConstructor
public class FuelSurchargeController {

    private final FuelSurchargeService fuelSurchargeService;

    @GetMapping
    public ResponseEntity<List<FuelSurcharge>> getAll() {
        return ResponseEntity.ok(fuelSurchargeService.getAll());
    }

    @GetMapping("/current")
    public ResponseEntity<FuelSurcharge> getCurrent() {
        return ResponseEntity.ok(fuelSurchargeService.getCurrentSurcharge());
    }

    @PostMapping
    public ResponseEntity<FuelSurcharge> create(
            @Valid @RequestBody FuelSurcharge surcharge) {
        FuelSurcharge created = fuelSurchargeService.create(surcharge);
        return ResponseEntity.created(URI.create("/api/fuel-surcharges/" + created.getUniqueID()))
                .body(created);
    }
}
