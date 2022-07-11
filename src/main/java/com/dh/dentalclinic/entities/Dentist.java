package com.dh.dentalclinic.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="dentists")
public class Dentist {

    @Id
    @SequenceGenerator(name = "dentist_sequence", sequenceName = "dentist_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dentist_sequence")
    private Long id;
    @Column
    private String name,surname;
    @Column
    private int registrationNumber;


    @OneToMany(mappedBy = "dentist", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Appointment> appointments= new HashSet<>();

    public Dentist() {
    }

    public Dentist(Long id, String name, String surname, int registrationNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.registrationNumber = registrationNumber;
    }

    public Dentist(String name, String surname, int registrationNumber) {
        this.name = name;
        this.surname = surname;
        this.registrationNumber = registrationNumber;
    }

}

