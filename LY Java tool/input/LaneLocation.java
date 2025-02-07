package com.ascendcargo.contractmgt.model;

@Entity
@Table(name = "lane_locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaneLocation {
    @EmbeddedId
    private LaneLocationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("laneId")
    private Lane lane;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("locationId")
    private Location location;

    @Column(insertable = false, updatable = false)
    private PointType type;

    private Integer sequence;

    public enum PointType {
        ORIGIN, DESTINATION, VIA_POINT
    }
}
// FILE_END