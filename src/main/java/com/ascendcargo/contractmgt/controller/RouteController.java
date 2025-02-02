package com.ascendcargo.contractmgt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ascendcargo.contractmgt.service.RouteEquipmentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

    //private final RouteService routeService;
    private final RouteEquipmentService routeEquipmentService;

    @PostMapping("/{routeId}/equipments/{equipmentId}")
    public ResponseEntity<Void> addEquipmentToRoute(
            @PathVariable Long routeId,
            @PathVariable Long equipmentId) {
        routeEquipmentService.addEquipmentToRoute(routeId, equipmentId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{routeId}/equipments/{equipmentId}")
    public ResponseEntity<Void> removeEquipmentFromRoute(
            @PathVariable Long routeId,
            @PathVariable Long equipmentId) {
        routeEquipmentService.removeEquipmentFromRoute(routeId, equipmentId);
        return ResponseEntity.noContent().build();
    }
}