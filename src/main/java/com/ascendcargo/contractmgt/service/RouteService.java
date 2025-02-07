package com.ascendcargo.contractmgt.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ascendcargo.contractmgt.model.Route;
import com.ascendcargo.contractmgt.repository.RouteRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final OrganizationService organizationService;
    private final RateService rateService;

    public Route createRoute(Route route) {
        
        Route savedRoute = routeRepository.save(route);

        // 处理费率
        if (route.getRates() != null) {
            route.getRates().forEach(rate -> {
                rate.setRoute(savedRoute);
                rateService.createRate(rate);
            });
        }
        return savedRoute;
    }

    public List<Route> findAvailableRoutes(Long laneId, LocalDate shipmentDate) {
        return routeRepository.findByLane_Id(laneId).stream()
                .filter(route -> isRouteAvailable(route, shipmentDate))
                .collect(Collectors.toList());
    }

    private boolean isRouteAvailable(Route route, LocalDate date) {
        return route.getRates().stream()
                .anyMatch(rate -> rate.isEffectiveOn(date));
    }

    private void validateCarrier(Long carrierId) {
        organizationService.getOrganization(carrierId); // 确保承运商存在
    }

    public List<Route> getRoutesByLaneId(Long laneId) {
        return routeRepository.findByLane_Id(laneId);
    }

    public Route getRouteById(Long id) {
        return routeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Route not found with id: " + id));
    }
}