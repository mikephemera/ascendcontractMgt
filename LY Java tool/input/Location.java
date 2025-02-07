package com.ascendcargo.contractmgt.model;

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
// FILE_END