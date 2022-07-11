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
    @JoinColumn(name = "domicile_id", referencedColumnName = "id")
    private Domicile domicile;


    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Appointment> appointments = new HashSet<>();

    public Patient(Long id, String name, String surname, String email, int documentNumber, Domicile domicile) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.documentNumber = documentNumber;
        this.domicile = domicile;
    }

    public Patient(String name, String surname, String email, int documentNumber, LocalDate dateAdmission, Domicile domicile) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.documentNumber = documentNumber;
        this.dateAdmission = dateAdmission;
        this.domicile = domicile;
    }

    public Patient(String name, String surname, String email, int documentNumber, Domicile domicile) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.documentNumber = documentNumber;
        this.domicile = domicile;
    }

    public Patient() {
    }

}