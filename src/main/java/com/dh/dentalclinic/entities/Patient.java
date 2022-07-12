package com.dh.dentalclinic.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Patients")
public class Patient {
    @Id
    @SequenceGenerator(name = "patient_sequence", sequenceName = "patient_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_sequence")
    private Long id;
    @Column
    private String name, surname, email;
    @Column
    private int documentNumber;
    @Column
    private LocalDate dateAdmission;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;


    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Appointment> appointments = new HashSet<>();

    public Patient(Long id, String name, String surname, String email, int documentNumber, Address address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.documentNumber = documentNumber;
        this.address = address;
    }

    public Patient(String name, String surname, String email, int documentNumber, LocalDate dateAdmission, Address address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.documentNumber = documentNumber;
        this.dateAdmission = dateAdmission;
        this.address = address;
    }

    public Patient(String name, String surname, String email, int documentNumber, Address address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.documentNumber = documentNumber;
        this.address = address;
    }

    public Patient() {
    }

}