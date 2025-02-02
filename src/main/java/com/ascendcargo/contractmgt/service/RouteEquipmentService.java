package com.ascendcargo.contractmgt.service;

import org.springframework.stereotype.Service;
import com.ascendcargo.contractmgt.model.Equipment;
import com.ascendcargo.contractmgt.model.Route;
import com.ascendcargo.contractmgt.repository.EquipmentRepository;
import com.ascendcargo.contractmgt.repository.RouteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RouteEquipmentService {

    private final RouteRepository routeRepository;
    private final EquipmentRepository equipmentRepository;

    public void addEquipmentToRoute(Long routeId, Long equipmentId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new EntityNotFoundException("Route not found"));
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new EntityNotFoundException("Equipment not found"));
        
        route.getEquipments().add(equipment);
        routeRepository.save(route);
    }

    public void removeEquipmentFromRoute(Long routeId, Long equipmentId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new EntityNotFoundException("Route not found"));
        route.getEquipments().removeIf(e -> e.getUniqueID().equals(equipmentId));
        routeRepository.save(route);
    }
}