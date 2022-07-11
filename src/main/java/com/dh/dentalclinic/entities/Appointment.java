package com.dh.dentalclinic.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@Entity

@Table(name = "Appointments")
public class Appointment {
    @Id
    @SequenceGenerator(name = "appointment_sequence", sequenceName = "appointment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_sequence")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Patient patient;

    public Appointment(Long id, Patient patient, Dentist dentist) {
        this.id = id;
        this.patient = patient;
        this.dentist = dentist;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dentist_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Dentist dentist;
    @Column
    private LocalDate date;

    public Appointment(Patient patient, Dentist dentist) {
        this.patient = patient;
        this.dentist = dentist;
    }

    public Appointment(Patient patient, Dentist dentist, LocalDate date) {
        this.patient = patient;
        this.dentist = dentist;
        this.date = date;
    }

    public Appointment() {
    }
}
