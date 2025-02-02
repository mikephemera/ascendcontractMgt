package com.ascendcargo.contractmgt.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ascendcargo.contractmgt.model.LocationType;
import com.ascendcargo.contractmgt.service.LocationTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/location-types")
@RequiredArgsConstructor
public class LocationTypeController {

    private final LocationTypeService locationTypeService;

    @GetMapping
    public ResponseEntity<List<LocationType>> getAllLocationTypes() {
        return ResponseEntity.ok(locationTypeService.getAllLocationTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationType> getLocationTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(locationTypeService.getLocationTypeById(id));
    }

    @PostMapping
    public ResponseEntity<LocationType> createLocationType(@Valid @RequestBody LocationType locationType) {
        LocationType created = locationTypeService.createLocationType(locationType);
        return ResponseEntity.created(URI.create("/api/location-types/" + created.getUniqueID()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationType> updateLocationType(
            @PathVariable Long id, 
            @Valid @RequestBody LocationType locationType) {
        return ResponseEntity.ok(locationTypeService.updateLocationType(id, locationType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocationType(@PathVariable Long id) {
        locationTypeService.deleteLocationType(id);
        return ResponseEntity.noContent().build();
    }
}
