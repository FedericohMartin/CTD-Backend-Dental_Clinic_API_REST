package com.dh.dentalclinic.services;

import com.dh.dentalclinic.entities.Appointment;
import com.dh.dentalclinic.exceptions.BadRequestException;
import com.dh.dentalclinic.exceptions.ResourceNotFoundException;
import com.dh.dentalclinic.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    public List<Appointment> listAppointments(){
        return repository.findAll();
    }

    public Optional<Appointment> findAppointment(Long id){
        return repository.findById(id);
    }

    public Appointment saveAppointment(Appointment appointment){
        return (Appointment) repository.save(appointment);
    }

    public Appointment updateAppointment(Appointment appointment) throws BadRequestException {
        if (findAppointment(appointment.getId()).isPresent()){
            return (Appointment) repository.save(appointment);
        }
        else {
            throw new BadRequestException("Appointment not found, update cancelled");
        }
    }

    public void deleteAppointment(Long id) throws ResourceNotFoundException {
        Optional<Appointment> appointment = findAppointment(id);
        if (appointment.isPresent())
            repository.deleteById(id);
        else
            throw new ResourceNotFoundException("Appointment with ID: " + id + " not exists, delete cancelled");
    }

}

