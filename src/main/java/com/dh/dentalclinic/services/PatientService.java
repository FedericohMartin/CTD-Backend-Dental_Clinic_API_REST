package com.dh.dentalclinic.services;

import com.dh.dentalclinic.entities.Patient;
import com.dh.dentalclinic.exceptions.BadRequestException;
import com.dh.dentalclinic.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<Patient> listPatients();

    Optional<Patient> findPatient(Long id);

    Patient savePatient(Patient patient);

    Patient updatePatient(Patient patient) throws BadRequestException;

    void deletePatient(Long id) throws ResourceNotFoundException;

    Optional<Patient> findByEmail(String email) throws ResourceNotFoundException;
}
