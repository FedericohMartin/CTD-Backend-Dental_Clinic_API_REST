package com.dh.dentalclinic.services.impl;

import com.dh.dentalclinic.entities.Patient;
import com.dh.dentalclinic.exceptions.BadRequestException;
import com.dh.dentalclinic.exceptions.ResourceNotFoundException;
import com.dh.dentalclinic.repositories.PatientRepository;
import com.dh.dentalclinic.services.PatientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImplementation implements PatientService {

    @Autowired
    private PatientRepository repository;
    private static final Logger logger = Logger.getLogger(PatientServiceImplementation.class);

    @Override
    public List<Patient> listPatients(){
        logger.info("Listing all patients");
        return repository.findAll();
    }

    @Override
    public Optional<Patient> findPatient(Long id){
        logger.info("Finding patient with ID :"+ id);
        return repository.findById(id);
    }

    @Override
    public Patient savePatient(Patient patient){
        logger.info("Saving new patient");
        return repository.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient)throws BadRequestException {
        if (findPatient(patient.getId()).isPresent()){
            logger.info("Updating patient with ID: " + patient.getId());
            return (Patient) repository.save(patient);
        }else{
            logger.error("Patient not found, update cancelled");
            throw new BadRequestException("Patient not found, update cancelled");
        }
    }

    @Override
    public void deletePatient(Long id) throws ResourceNotFoundException {
        Optional<Patient> patient = findPatient(id);
        if(patient.isPresent()){
            logger.info("Deleting patient with ID: "+ id);
            repository.deleteById(id);
        }else{
            logger.error("Patient with ID: " + id + " not found, delete cancelled");
            throw new ResourceNotFoundException("Patient with ID: " + id + " not found, delete cancelled");
        }
    }

    @Override
    public Optional<Patient> findByEmail(String email) throws ResourceNotFoundException {
        Optional<Patient> patient = repository.findPatientByEmail(email);
        if (patient.isPresent()){
            logger.info("Finding patient with email: "+ email);
            return patient;
        }else {
            logger.error("Patient with email: " + email + " not found");
            throw new ResourceNotFoundException("Patient with email: " + email + " not found");
        }
    }


}
