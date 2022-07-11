package com.dh.dentalclinic.services;

import com.dh.dentalclinic.entities.Patient;
import com.dh.dentalclinic.exceptions.BadRequestException;
import com.dh.dentalclinic.exceptions.ResourceNotFoundException;
import com.dh.dentalclinic.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public List<Patient> listPatients(){
        return repository.findAll();
    }

    public Optional<Patient> findPatient(Long id){
        Optional<Patient> patient = repository.findById(id);
        return repository.findById(id);
    }

    public Patient savePatient(Patient patient){
        return repository.save(patient);
    }

    public Patient updatePatient (Patient patient)throws BadRequestException {

        if (findPatient(patient.getId()).isPresent()){
            return (Patient) repository.save(patient);
        }else{
            throw new BadRequestException("Patient not found, update cancelled ");
        }

    }

    public void deletePatient(Long id) throws ResourceNotFoundException {
        Optional<Patient> patient = findPatient(id);
        if(patient.isPresent()){
            repository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Patient with ID: " + id + " not found, delete cancelled");
        }

    }

    public Optional<Patient> findByEmail(String email) throws ResourceNotFoundException {
        Optional<Patient> patient = repository.findPatientByEmail(email);
        if (patient.isPresent()){
            return patient;
        }else {
            throw new ResourceNotFoundException("Patient with email: " + email + " not found");
        }
    }


}
