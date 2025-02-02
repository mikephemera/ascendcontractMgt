package com.ascendcargo.contractmgt.service;

import java.util.List;
import com.ascendcargo.contractmgt.model.Location;

public interface LocationService {
    Location createLocation(Location location);
    Location createLocationWithType(String locationValue, Long locationTypeId);
    List<Location> getAllLocations();
    Location getLocationById(Long id);
    Location updateLocation(Long id, Location location);
    void deleteLocation(Long id);
    List<Location> getLocationsByType(String typeName);
}

