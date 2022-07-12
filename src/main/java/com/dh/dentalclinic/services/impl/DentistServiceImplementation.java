package com.dh.dentalclinic.services.impl;

import com.dh.dentalclinic.entities.Dentist;
import com.dh.dentalclinic.exceptions.BadRequestException;
import com.dh.dentalclinic.exceptions.ResourceNotFoundException;
import com.dh.dentalclinic.repositories.DentistRepository;
import com.dh.dentalclinic.services.DentistService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistServiceImplementation implements DentistService {

    @Autowired
    private DentistRepository repository;
    private static final Logger logger = Logger.getLogger(DentistServiceImplementation.class);

    @Override
    public List<Dentist> listDentists(){
        logger.info("Listing all dentists");
        return repository.findAll();
    }

    @Override
    public Optional<Dentist> findDentist(Long id){
        logger.info("Finding dentist with ID :"+ id);
        return repository.findById(id);
    }

    @Override
    public Dentist saveDentist(Dentist dentist){
        logger.info("Saving new dentist");
        return (Dentist) repository.save(dentist);
    }

    @Override
    public Dentist updateDentist(Dentist dentist) throws BadRequestException {
        if (findDentist(dentist.getId()).isPresent()){
            logger.info("Updating dentist with ID: " + dentist.getId());
            return (Dentist) repository.save(dentist);
        }
        else {
            logger.error("Dentist not found, update cancelled");
            throw new BadRequestException("Dentist not found, update cancelled");
        }
    }

    @Override
    public void deleteDentist(Long id) throws ResourceNotFoundException {
        Optional<Dentist> dentist = findDentist(id);
        if (dentist.isPresent()){
            logger.info("Deleting dentist with ID: "+ id);
            repository.deleteById(id);
        }else
            logger.error("Dentist with ID: "+ id + " doesn't exist, delete cancelled");
            throw new ResourceNotFoundException("Dentist with ID: " + id + " doesn't exist, delete cancelled");
    }
}