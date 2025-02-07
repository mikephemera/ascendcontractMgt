package com.ascendcargo.contractmgt.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ascendcargo.contractmgt.model.Lane;
import com.ascendcargo.contractmgt.repository.LaneRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class LaneService {
    private final LaneRepository laneRepository;
    private final RouteService routeService;

    public Lane createLane(Lane lane) {
        validateFacilityTypes(lane);
        Lane savedLane = laneRepository.save(lane);


        // 处理运输路线
        if (lane.getRoutes() != null) {
            lane.getRoutes().forEach(route -> {
                route.setLane(savedLane);
                routeService.createRoute(route);
            });
        }
        return savedLane;
    }

    private void validateFacilityTypes(Lane lane) {
        if (lane.getOriginType() == lane.getDestinationType() && 
            lane.getOriginType() == Lane.FacilityType.PORT) {
            throw new IllegalArgumentException("Port-to-port lanes require special approval");
        }
    }

    public Lane getLaneById(Long id) {
        return laneRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Lane not found with id: " + id));
    }

    public List<Lane> getLanesByContractId(Long contractId) {
        return laneRepository.findByContractId(contractId);
    }
}