package com.dh.dentalclinic.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "domiciles")
public class Domicile {
    @Id
    @SequenceGenerator(name = "domicile_sequence",sequenceName = "domicile_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "domicile_sequence")
    private Long id;
    @Column
    private String street;
    @Column
    private int streetNumber;
    @Column
    private String city;
    @Column
    private String province;

}
