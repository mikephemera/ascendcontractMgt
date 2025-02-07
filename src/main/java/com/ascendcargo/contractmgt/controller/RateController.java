package com.ascendcargo.contractmgt.controller;

import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ascendcargo.contractmgt.service.RateService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rates")
@RequiredArgsConstructor

public class RateController {
    private final RateService rateService;

    @GetMapping("/{rateId}/calculate")
    public ResponseEntity<BigDecimal> calculateCharge(@PathVariable Long rateId,
            @RequestParam BigDecimal weight) {
        return ResponseEntity
                .ok(rateService.calculateCharge(rateService.getRateById(rateId), weight));
    }
}
