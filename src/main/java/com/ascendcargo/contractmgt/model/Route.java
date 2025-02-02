package com.ascendcargo.contractmgt.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Routes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueID;

    @Column(nullable = false)
    private String mode;

    @Column(precision = 10, scale = 2)
    private BigDecimal capacity;

    @Column(precision = 10, scale = 2)
    private BigDecimal distance;

    @Column(columnDefinition = "varchar(20) default 'Miles'")
    private String unit;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToMany
    @JoinTable(
        name = "RouteEquipment",
        joinColumns = @JoinColumn(name = "RouteID"),
        inverseJoinColumns = @JoinColumn(name = "EquipmentID")
    )
    private List<Equipment> equipments = new ArrayList<>();

    public Object getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }

    public void setName(Object name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setName'");
    }
}