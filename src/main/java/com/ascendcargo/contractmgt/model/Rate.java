package com.ascendcargo.contractmgt.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Rates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rate {
    public enum ContractType {
        Contracted, Kniff
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueID;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContractType contractedOrKniff;

    @Column(precision = 15, scale = 2)
    private BigDecimal cost;

    @Column(precision = 15, scale = 2)
    private BigDecimal revenue;

    @Column(length = 3)
    private String currency = "USD";

    private LocalDate startDate;
    private LocalDate endDate;

    private String rateBasis;

    @ManyToMany(mappedBy = "rates")
    private List<Lane> lanes = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ContractType contractType;
}
