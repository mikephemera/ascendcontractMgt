package com.ascendcargo.contractmgt.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String type;

    @Column(name = "iso_code")
    private String isoCode;

    @Column(precision = 10, scale = 2)
    private BigDecimal capacity;

    @Enumerated(EnumType.STRING)
    private EquipmentUnit unit;

    public enum EquipmentUnit {
        TEU, // 对应数据库枚举值 TEU
        FEU, // 对应数据库枚举值 FEU
        TON, // 对应数据库枚举值 ton
        CBM // 对应数据库枚举值 cbm
    }
}
