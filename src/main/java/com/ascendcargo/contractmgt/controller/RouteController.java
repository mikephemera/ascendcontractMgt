package com.ascendcargo.contractmgt.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ascendcargo.contractmgt.model.Rate;
import com.ascendcargo.contractmgt.model.Route;
import com.ascendcargo.contractmgt.service.RateService;
import com.ascendcargo.contractmgt.service.RouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;
    private final RateService rateService;

    @GetMapping("/{id}")
    public ResponseEntity<Route> getRoute(@PathVariable Long id) {
        return ResponseEntity.ok(routeService.getRouteById(id));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Route>> findAvailableRoutes(
            @RequestParam Long laneId,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate shipmentDate
    ) {
        return ResponseEntity.ok(routeService.findAvailableRoutes(laneId, shipmentDate));
    }

    @PostMapping("/{routeId}/rates")
    public ResponseEntity<Rate> createRate(
            @PathVariable Long routeId,
            @Valid @RequestBody Rate rate
    ) {
        Route route = routeService.getRouteById(routeId);
        rate.setRoute(route);
        return ResponseEntity.status(HttpStatus.CREATED).body(rateService.createRate(rate));
    }

    @GetMapping("/{routeId}/rates")
    public ResponseEntity<List<Rate>> getRouteRates(@PathVariable Long routeId) {
        return ResponseEntity.ok(rateService.getRatesByRouteId(routeId));
    }
}