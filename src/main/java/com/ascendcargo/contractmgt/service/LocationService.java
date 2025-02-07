package com.ascendcargo.contractmgt.service;

import com.ascendcargo.contractmgt.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    Location updateLocation(Long id, Location locationDetails);
    Optional<Location> getLocationById(Long id);
    List<Location> getAllLocations();
    Location addLocation(Location location);
    void deleteLocation(Long id);
}