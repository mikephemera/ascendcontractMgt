package com.ascendcargo.contractmgt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ascendcargo.contractmgt.model.LaneLocation;
import com.ascendcargo.contractmgt.model.Location;
import com.ascendcargo.contractmgt.repository.LocationRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public Location createLocation(Location location) {
        if (location.getCode() != null && locationRepository.existsByCode(location.getCode())) {
            throw new IllegalStateException("Location code must be unique");
        }
        return locationRepository.save(location);
    }

    public void validateLocationType(Location location, LaneLocation.PointType pointType) {
        switch (pointType) {
            case ORIGIN:
                if (!location.getType().equals(Location.LocationType.PORT)) {
                    throw new IllegalArgumentException("Origin point must be a port");
                }
                break;
            case DESTINATION:
                if (location.getType().equals(Location.LocationType.CUSTOMS)) {
                    throw new IllegalArgumentException("Customs cannot be destination");
                }
                break;
        }
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Location not found with id: " + id));
    }

    public Location getLocationByCode(String code) {
        return locationRepository.findByCode(code)
            .orElseThrow(() -> new IllegalArgumentException("Location not found with code: " + code));
    }
}