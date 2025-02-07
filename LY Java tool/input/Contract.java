package com.ascendcargo.contractmgt.model;

@Entity
@Table(name = "contracts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", nullable = false)
    private Organization organization;

    @Column(name = "contract_ref", unique = true)
    private String contractReference;

    @Enumerated(EnumType.STRING)
    private ContractStatus status = ContractStatus.DRAFT;

    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private Set<Lane> lanes = new HashSet<>();

    public enum ContractStatus {
        DRAFT, ACTIVE, EXPIRED
    }
}
// FILE_END