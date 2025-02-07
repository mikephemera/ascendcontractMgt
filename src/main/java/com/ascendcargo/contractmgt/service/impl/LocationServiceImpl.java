package com.ascendcargo.contractmgt.service.impl;

import com.ascendcargo.contractmgt.service.LocationService;

import com.ascendcargo.contractmgt.model.Location;
import com.ascendcargo.contractmgt.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    public Location updateLocation(Long id, Location locationDetails) {
        Optional<Location> optionalLocation = locationRepository.findById(id);
        if (optionalLocation.isPresent()) {
            Location location = optionalLocation.get();
            location.setLocationName(locationDetails.getLocationName());
            location.setAddressLine1(locationDetails.getAddressLine1());
            location.setAddressLine2(locationDetails.getAddressLine2());
            location.setCity(locationDetails.getCity());
            location.setState(locationDetails.getState());
            location.setZipCode(locationDetails.getZipCode());
            location.setCountry(locationDetails.getCountry());
            location.setLatitude(locationDetails.getLatitude());
            location.setLongitude(locationDetails.getLongitude());
            location.setIsHeadquarters(locationDetails.getIsHeadquarters());
            return locationRepository.save(location);
        }
        return null;
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}