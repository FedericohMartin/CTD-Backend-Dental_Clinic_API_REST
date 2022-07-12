package com.dh.dentalclinic.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @SequenceGenerator(name = "address_sequence",sequenceName = "address_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "address_sequence")
    private Long id;
    @Column
    private String street;
    @Column
    private int streetNumber;
    @Column
    private String city;
    @Column
    private String province;

    public Address(Long id, String street, int streetNumber, String city, String province) {
        this.id = id;
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.province = province;
    }

    public Address(String street, int streetNumber, String city, String province) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.province = province;
    }

    public Address() {
    }
}
