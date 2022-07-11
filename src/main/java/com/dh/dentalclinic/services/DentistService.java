package com.dh.dentalclinic.services;

import com.dh.dentalclinic.entities.Dentist;
import com.dh.dentalclinic.exceptions.BadRequestException;
import com.dh.dentalclinic.exceptions.ResourceNotFoundException;
import com.dh.dentalclinic.repositories.DentistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistService {

    @Autowired
    private DentistRepository repository;

    public List<Dentist> listDentists(){
        return repository.findAll();
    }

    public Optional<Dentist> findDentist(Long id){
        return repository.findById(id);
    }

    public Dentist saveDentist(Dentist dentist){
        return (Dentist) repository.save(dentist);
    }

    public Dentist updateDentist(Dentist dentist) throws BadRequestException {
        if (findDentist(dentist.getId()).isPresent()){
            return (Dentist) repository.save(dentist);
        }
        else {
            throw new BadRequestException("Dentist not found, update cancelled");
        }
    }

    public void deleteDentist(Long id) throws ResourceNotFoundException {
        Optional<Dentist> dentist = findDentist(id);
        if (dentist.isPresent())
            repository.deleteById(id);
        else
            throw new ResourceNotFoundException("Patient with ID: " + id + " doesn't exist, delete cancelled");
    }
}