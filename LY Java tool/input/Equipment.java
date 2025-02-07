package com.ascendcargo.contractmgt.model;

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
// FILE_END