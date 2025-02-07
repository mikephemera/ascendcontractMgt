package com.ascendcargo.contractmgt.model;

import org.springframework.data.geo.Point;
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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization organization;

    @Enumerated(EnumType.STRING)
    private LocationType type;

    private String code;
    private String name;
    private String address;
    private String city;
    private String state;

    @Column(length = 2)
    private String country;

    @Column(columnDefinition = "POINT SRID 4326")
    //TO DO
    //@Type(value = org.hibernate.spatial.GeometryType.class)
    private Point coordinates;

    public enum LocationType {
        PORT, TERMINAL, WAREHOUSE, CUSTOMS
    }
}