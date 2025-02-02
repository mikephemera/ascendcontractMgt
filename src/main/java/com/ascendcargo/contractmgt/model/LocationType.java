package com.ascendcargo.contractmgt.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LocationType")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueID;

    @Column(nullable = false, unique = true)
    private String typeName;

    @OneToMany(mappedBy = "locationType", cascade = CascadeType.ALL)
    private List<Location> locations = new ArrayList<>();
}