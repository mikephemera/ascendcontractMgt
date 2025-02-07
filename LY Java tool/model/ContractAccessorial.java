package com.ascendcargo.contractmgt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Entity
@Table(name = "contract_accessorials")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractAccessorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccessorialType type;

    @Lob // 对应TEXT类型
    @Column(name = "calculation_method")
    private String calculationMethod;

    public enum AccessorialType {
        FUEL_SURCHARGE, // 对应数据库枚举值 fuel_surcharge
        DETENTION, // 对应数据库枚举值 detention
        DEMURRAGE // 对应数据库枚举值 demurrage
    }

    public class Type {// to do
    }
}
