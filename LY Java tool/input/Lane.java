package com.ascendcargo.contractmgt.model;

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
// FILE_END