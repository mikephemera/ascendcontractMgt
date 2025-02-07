package com.ascendcargo.contractmgt.controller;

import java.math.BigDecimal;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ascendcargo.contractmgt.service.RateService;
import com.ascendcargo.contractmgt.service.RateService.TotalCharge;
import com.ascendcargo.contractmgt.model.Rate;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rates")
@RequiredArgsConstructor

public class RateController {
    private final RateService rateService;

    @GetMapping("/{rateId}/calculate") // 仅基础费率
    public ResponseEntity<BigDecimal> calculateCharge(@PathVariable Long rateId,
            @RequestParam BigDecimal weight) {
        return ResponseEntity
                .ok(rateService.calculateCharge(rateService.getRateById(rateId), weight));
    }

    @PostMapping("/calculate") // 基础费率+多级附加费
    public ResponseEntity<TotalCharge> calculateTotalCharge(@RequestParam Long rateId,
            @RequestParam BigDecimal weight, @RequestBody Map<String, Object> context) {

        Rate rate = rateService.getRateById(rateId);
        return ResponseEntity.ok(rateService.calculateTotalCharge(rate, weight, context));
    }
}
