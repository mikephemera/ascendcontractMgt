package com.ascendcargo.contractmgt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Location")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LocationTypeID", nullable = false)
    private LocationType locationType;

    @Column(nullable = false)
    private String locationValue;
}
