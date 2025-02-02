package com.ascendcargo.contractmgt.model;

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
@Table(name = "Accessorial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accessorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueID;

    @Column(nullable = false)
    private String serviceType;
}