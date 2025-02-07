package com.ascendcargo.contractmgt.model;

import java.math.BigDecimal;
import java.util.Map;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private ApplicationScope scope; // 适用范围（CONTRACT/LANE/ROUTE）

    @ManyToOne
    @JoinColumn(name = "lane_id")
    private Lane lane;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    // 新增计算逻辑解析方法
    public BigDecimal calculate(BigDecimal baseAmount, Map<String, Object> context) {
        String[] parts = calculationMethod.split(":");
        String type = parts[0];
        BigDecimal value = new BigDecimal(parts[1]);

        switch (type) {
            case "percentage":
                return baseAmount.multiply(value).divide(BigDecimal.valueOf(100));
            case "daily":
                Integer days = (Integer) context.get("days");
                return value.multiply(BigDecimal.valueOf(days));
            case "fixed":
                return value;
            default:
                throw new UnsupportedOperationException("Unsupported calculation type: " + type);
        }
    }

    public enum ApplicationScope {
        CONTRACT, LANE, ROUTE
    }
}
