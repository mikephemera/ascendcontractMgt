package com.ascendcargo.contractmgt.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FuelSurcharge")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuelSurcharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueID;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal surchargeRate;

    @Column(nullable = false)
    private LocalDate effectiveDate;
}