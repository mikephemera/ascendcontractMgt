package com.ascendcargo.contractmgt.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ascendcargo.contractmgt.model.Rate;
import com.ascendcargo.contractmgt.service.RateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rates")
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;

    @GetMapping
    public ResponseEntity<List<Rate>> getAllRates() {
        return ResponseEntity.ok(rateService.getAllRates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rate> getRateById(@PathVariable Long id) {
        return ResponseEntity.ok(rateService.getRateById(id));
    }

    @PostMapping
    public ResponseEntity<Rate> createRate(@Valid @RequestBody Rate rate) {
        Rate created = rateService.createRate(rate);
        return ResponseEntity.created(URI.create("/api/rates/" + created.getUniqueID()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rate> updateRate(
            @PathVariable Long id,
            @Valid @RequestBody Rate rate) {
        return ResponseEntity.ok(rateService.updateRate(id, rate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRate(@PathVariable Long id) {
        rateService.deleteRate(id);
        return ResponseEntity.noContent().build();
    }
}
