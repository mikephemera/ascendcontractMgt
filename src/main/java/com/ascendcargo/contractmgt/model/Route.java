package com.ascendcargo.contractmgt.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "routes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lane_id", nullable = false)
    private Lane lane;

    @Enumerated(EnumType.STRING)
    private TransportMode mode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_org_id", nullable = false)
    private Organization carrier;

    @Column(name = "equipment_type")
    private String equipmentType;

    private BigDecimal distance;

    @Enumerated(EnumType.STRING)
    @Column(name = "distance_unit")
    private DistanceUnit distanceUnit;

    @Column(name = "transit_time")
    private Integer transitTime; // hours

    private Integer capacity;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private Set<Rate> rates = new HashSet<>();

    public enum TransportMode {
        SEA, RAIL, TRUCK, AIR
    }

    public enum DistanceUnit {
        KM, MILE
    }
}