package com.ascendcargo.contractmgt.model;

import java.io.Serializable;
import com.ascendcargo.contractmgt.model.LaneLocation.PointType;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaneLocationId implements Serializable {
    private Long laneId;
    private Long locationId;
    private PointType type;
}