package com.ascendcargo.contractmgt.model;


import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Lane")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OriginLocationID", nullable = false)
    private Location originLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DestinationLocationID", nullable = false)
    private Location destinationLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ViaPointLocationID")
    private Location viaPointLocation;

    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
        name = "LaneRates",
        joinColumns = @JoinColumn(name = "LaneID"),
        inverseJoinColumns = @JoinColumn(name = "RateID")
    )
    private List<Rate> rates = new ArrayList<>();
}