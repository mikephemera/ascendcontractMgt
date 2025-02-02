package com.ascendcargo.contractmgt.service;

import java.util.List;
import com.ascendcargo.contractmgt.model.LocationType;

public interface LocationTypeService {
    LocationType createLocationType(LocationType locationType);
    List<LocationType> getAllLocationTypes();
    LocationType getLocationTypeById(Long id);
    LocationType updateLocationType(Long id, LocationType locationType);
    void deleteLocationType(Long id);
}

