package com.ascendcargo.contractmgt.model;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaneLocationId implements Serializable {
    private Long laneId;
    private Long locationId;
    private PointType type;
}
// FILE_END