package com.ascendcargo.contractmgt.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ascendcargo.contractmgt.model.Location;
import com.ascendcargo.contractmgt.model.LocationType;
import com.ascendcargo.contractmgt.repository.LocationRepository;
import com.ascendcargo.contractmgt.repository.LocationTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LocationServiceImpl implements LocationService {
    
    private final LocationRepository locationRepository;
    private final LocationTypeRepository locationTypeRepository;

    @Override
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location createLocationWithType(String locationValue, Long locationTypeId) {
        LocationType type = locationTypeRepository.findById(locationTypeId)
                .orElseThrow(() -> new EntityNotFoundException("LocationType not found"));
        
        return locationRepository.save(
            Location.builder()
                .locationValue(locationValue)
                .locationType(type)
                .build()
        );
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with id: " + id));
    }

    @Override
    public Location updateLocation(Long id, Location location) {
        Location existing = getLocationById(id);
        existing.setLocationValue(location.getLocationValue());
        existing.setLocationType(location.getLocationType());
        return locationRepository.save(existing);
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public List<Location> getLocationsByType(String typeName) {
        return locationRepository.findByLocationType_TypeName(typeName);
    }
}