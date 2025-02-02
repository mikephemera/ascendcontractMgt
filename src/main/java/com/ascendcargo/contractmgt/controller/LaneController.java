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
import com.ascendcargo.contractmgt.model.Lane;
import com.ascendcargo.contractmgt.service.LaneDTO;
import com.ascendcargo.contractmgt.service.LaneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/lanes")
@RequiredArgsConstructor
public class LaneController {

    private final LaneService laneService;

    @GetMapping
    public ResponseEntity<List<Lane>> getAllLanes() {
        return ResponseEntity.ok(laneService.getAllLanes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lane> getLaneById(@PathVariable Long id) {
        return ResponseEntity.ok(laneService.getLaneById(id));
    }

    @PostMapping
    public ResponseEntity<Lane> createLane(@Valid @RequestBody LaneDTO laneDTO) {
        Lane created = laneService.createLane(laneDTO);
        return ResponseEntity.created(URI.create("/api/lanes/" + created.getUniqueID()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lane> updateLane(
            @PathVariable Long id,
            @Valid @RequestBody LaneDTO laneDTO) {
        return ResponseEntity.ok(laneService.updateLane(id, laneDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLane(@PathVariable Long id) {
        laneService.deleteLane(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{laneId}/rates/{rateId}")
    public ResponseEntity<Void> addRateToLane(
            @PathVariable Long laneId,
            @PathVariable Long rateId) {
        laneService.addRateToLane(laneId, rateId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{laneId}/rates/{rateId}")
    public ResponseEntity<Void> removeRateFromLane(
            @PathVariable Long laneId,
            @PathVariable Long rateId) {
        laneService.removeRateFromLane(laneId, rateId);
        return ResponseEntity.noContent().build();
    }
}
