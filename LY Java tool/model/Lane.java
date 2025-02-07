package com.ascendcargo.contractmgt.model;

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
@Table(name = "lanes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "origin_type")
    private FacilityType originType;

    @Enumerated(EnumType.STRING)
    @Column(name = "destination_type")
    private FacilityType destinationType;

    @OneToMany(mappedBy = "lane", cascade = CascadeType.ALL)
    private Set<Route> routes = new HashSet<>();

    @OneToMany(mappedBy = "lane", cascade = CascadeType.ALL)
    private Set<LaneLocation> laneLocations = new HashSet<>();

    public enum FacilityType {
        TERMINAL, WAREHOUSE, PORT
    }
}
