package com.ascendcargo.contractmgt.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ascendcargo.contractmgt.model.Lane;
import com.ascendcargo.contractmgt.model.Route;
import com.ascendcargo.contractmgt.service.LaneService;
import com.ascendcargo.contractmgt.service.RouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/lanes")
@RequiredArgsConstructor
public class LaneController {
    private final LaneService laneService;
    private final RouteService routeService;

    @GetMapping("/{id}")
    public ResponseEntity<Lane> getLane(@PathVariable Long id) {
        return ResponseEntity.ok(laneService.getLaneById(id));
    }

    @PostMapping("/{laneId}/routes")
    public ResponseEntity<Route> createRoute(
            @PathVariable Long laneId,
            @Valid @RequestBody Route route
    ) {
        Lane lane = laneService.getLaneById(laneId);
        route.setLane(lane);
        return ResponseEntity.status(HttpStatus.CREATED).body(routeService.createRoute(route));
    }

    @GetMapping("/{laneId}/routes")
    public ResponseEntity<List<Route>> getLaneRoutes(@PathVariable Long laneId) {
        return ResponseEntity.ok(routeService.getRoutesByLaneId(laneId));
    }
}