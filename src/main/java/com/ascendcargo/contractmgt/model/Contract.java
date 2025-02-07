package com.ascendcargo.contractmgt.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contracts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id", nullable = false)
    private Organization organization;

    @Column(name = "contract_ref", unique = true)
    private String contractReference;

    @Enumerated(EnumType.STRING)
    private ContractStatus status = ContractStatus.DRAFT;

    @Column(name = "effective_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate effectiveDate;

    @Column(name = "expiration_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Lane> lanes = new HashSet<>();

    public enum ContractStatus {
        DRAFT, ACTIVE, EXPIRED
    }

}
