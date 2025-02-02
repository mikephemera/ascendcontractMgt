package com.ascendcargo.contractmgt.service;

import java.util.List;
import com.ascendcargo.contractmgt.model.Lane;

public interface LaneService {
    Lane createLane(LaneDTO laneDTO);
    Lane getLaneById(Long id);
    List<Lane> getAllLanes();
    Lane updateLane(Long id, LaneDTO laneDTO);
    void deleteLane(Long id);
    void addRateToLane(Long laneId, Long rateId);
    void removeRateFromLane(Long laneId, Long rateId);
}

