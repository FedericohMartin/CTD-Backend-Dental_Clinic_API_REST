package com.dh.dentalclinic.services;

import com.dh.dentalclinic.entities.Dentist;
import com.dh.dentalclinic.exceptions.BadRequestException;
import com.dh.dentalclinic.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DentistService {
    List<Dentist> listDentists();

    Optional<Dentist> findDentist(Long id);

    Dentist saveDentist(Dentist dentist);

    Dentist updateDentist(Dentist dentist) throws BadRequestException;

    void deleteDentist(Long id) throws ResourceNotFoundException;
}
