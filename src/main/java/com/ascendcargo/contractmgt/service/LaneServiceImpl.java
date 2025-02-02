package com.ascendcargo.contractmgt.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ascendcargo.contractmgt.model.Lane;
import com.ascendcargo.contractmgt.model.Location;
import com.ascendcargo.contractmgt.model.Rate;
import com.ascendcargo.contractmgt.repository.LaneRepository;
import com.ascendcargo.contractmgt.repository.LocationRepository;
import com.ascendcargo.contractmgt.repository.RateRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LaneServiceImpl implements LaneService {

    private final LaneRepository laneRepository;
    private final LocationRepository locationRepository;
    private final RateRepository rateRepository;

    @Override
    public Lane createLane(LaneDTO laneDTO) {
        Location origin = locationRepository.findById(laneDTO.getOriginLocationId())
                .orElseThrow(() -> new EntityNotFoundException("Origin location not found"));
        Location destination = locationRepository.findById(laneDTO.getDestinationLocationId())
                .orElseThrow(() -> new EntityNotFoundException("Destination location not found"));
        Location viaPoint = Optional.ofNullable(laneDTO.getViaPointLocationId())
                .map(id -> locationRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Via point location not found")))
                .orElse(null);

        Lane lane = Lane.builder().originLocation(origin).destinationLocation(destination)
                .viaPointLocation(viaPoint).name(laneDTO.getName())
                .description(laneDTO.getDescription()).build();

        return laneRepository.save(lane);
    }

    @Override
    public Lane getLaneById(Long id) {
        return laneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lane not found with id: " + id));
    }

    @Override
    public List<Lane> getAllLanes() {
        return laneRepository.findAll();
    }

    @Override
    public Lane updateLane(Long id, LaneDTO laneDTO) {
        Lane existing = getLaneById(id);

        if (laneDTO.getOriginLocationId() != null) {
            existing.setOriginLocation(locationRepository.findById(laneDTO.getOriginLocationId())
                    .orElseThrow(() -> new EntityNotFoundException("Origin location not found")));
        }

        if (laneDTO.getDestinationLocationId() != null) {
            existing.setDestinationLocation(
                    locationRepository.findById(laneDTO.getDestinationLocationId()).orElseThrow(
                            () -> new EntityNotFoundException("Destination location not found")));
        }

        // 类似处理其他字段...
        return laneRepository.save(existing);
    }

    @Override
    public void deleteLane(Long id) {
        laneRepository.deleteById(id);
    }

    @Override
    public void addRateToLane(Long laneId, Long rateId) {
        Lane lane = getLaneById(laneId);
        Rate rate = rateRepository.findById(rateId)
                .orElseThrow(() -> new EntityNotFoundException("Rate not found"));

        lane.getRates().add(rate);
        laneRepository.save(lane);
    }

    @Override
    public void removeRateFromLane(Long laneId, Long rateId) {
        Lane lane = getLaneById(laneId);
        lane.getRates().removeIf(rate -> rate.getUniqueID().equals(rateId));
        laneRepository.save(lane);
    }
}
