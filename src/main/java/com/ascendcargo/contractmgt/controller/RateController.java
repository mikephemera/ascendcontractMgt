package com.ascendcargo.contractmgt.controller;

import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ascendcargo.contractmgt.service.RateService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rates")
@RequiredArgsConstructor
public class RateController {
    private final RateService rateService;

    @PostMapping("/{rateId}/calculate")
    public ResponseEntity<BigDecimal> calculateCharge(
            @PathVariable Long rateId,
            @RequestParam BigDecimal weight
    ) {
        return ResponseEntity.ok(rateService.calculateCharge(
            rateService.getRateById(rateId), 
            weight
        ));
    }
}