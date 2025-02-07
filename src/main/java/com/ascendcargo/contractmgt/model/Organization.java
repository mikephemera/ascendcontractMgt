package com.ascendcargo.contractmgt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uniqueid")
    private Long uniqueid;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 15)
    private String phone;

    @Email
    @Size(max = 100)
    private String email;

    @Size(max = 15)
    private String fax;

    @Column(name = "parent_org_id")
    private Long parentOrgID;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "org_location",
            joinColumns = @JoinColumn(name = "uniqueid"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    private Location Location; // = new HashSet<>();

}