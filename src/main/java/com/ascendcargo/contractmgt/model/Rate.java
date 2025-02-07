package com.ascendcargo.contractmgt.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @Enumerated(EnumType.STRING)
    @Column(name = "basis_type")
    private RateBasisType basisType;

    @Column(length = 3)
    private String currency;

    @Column(precision = 12, scale = 4)
    private BigDecimal cost;

    @Column(precision = 12, scale = 4)
    private BigDecimal revenue;

    @Column(name = "min_charge", precision = 12, scale = 2)
    private BigDecimal minCharge;

    @Column(name = "max_charge", precision = 12, scale = 2)
    private BigDecimal maxCharge;

    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    public enum RateBasisType {
        FLAT, PERCENTAGE, PER_KG, PER_LB, PER_CU_FT
    }

    public boolean isEffectiveOn(LocalDate date) {
        return (date.isEqual(effectiveDate) || date.isAfter(effectiveDate)) 
            && (expirationDate == null || date.isBefore(expirationDate) || date.isEqual(expirationDate));
    }
}