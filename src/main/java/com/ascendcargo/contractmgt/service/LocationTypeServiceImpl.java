package com.ascendcargo.contractmgt.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ascendcargo.contractmgt.model.LocationType;
import com.ascendcargo.contractmgt.repository.LocationTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LocationTypeServiceImpl implements LocationTypeService {

    private final LocationTypeRepository locationTypeRepository;

    @Override
    public LocationType createLocationType(LocationType locationType) {
        if (locationTypeRepository.existsByTypeName(locationType.getTypeName())) {
            throw new IllegalArgumentException(
                    "Location type already exists: " + locationType.getTypeName());
        }
        return locationTypeRepository.save(locationType);
    }

    @Override
    public List<LocationType> getAllLocationTypes() {
        return locationTypeRepository.findAll();
    }

    @Override
    public LocationType getLocationTypeById(Long id) {
        return locationTypeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("LocationType not found with id: " + id));
    }

    @Override
    public LocationType updateLocationType(Long id, LocationType locationType) {
        LocationType existing = getLocationTypeById(id);
        existing.setTypeName(locationType.getTypeName());
        return locationTypeRepository.save(existing);
    }

    @Override
    public void deleteLocationType(Long id) {
        locationTypeRepository.deleteById(id);
    }
}
