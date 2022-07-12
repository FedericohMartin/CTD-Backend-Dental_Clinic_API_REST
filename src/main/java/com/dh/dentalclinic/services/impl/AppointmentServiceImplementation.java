package com.dh.dentalclinic.services.impl;

import com.dh.dentalclinic.entities.Appointment;
import com.dh.dentalclinic.exceptions.BadRequestException;
import com.dh.dentalclinic.exceptions.ResourceNotFoundException;
import com.dh.dentalclinic.repositories.AppointmentRepository;
import com.dh.dentalclinic.services.AppointmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImplementation implements AppointmentService {

    @Autowired
    private AppointmentRepository repository;
    private static final Logger logger = Logger.getLogger(AppointmentServiceImplementation.class);

    @Override
    public List<Appointment> listAppointments(){
        logger.info("Listing all appointments");
        return repository.findAll();
    }

    @Override
    public Optional<Appointment> findAppointment(Long id){
        logger.info("Finding appointment with ID :"+ id);
        return repository.findById(id);
    }

    @Override
    public Appointment saveAppointment(Appointment appointment){
        logger.info("Saving new appointment");
        return (Appointment) repository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) throws BadRequestException {
        if (findAppointment(appointment.getId()).isPresent()){
            logger.info("Appointment with ID: "+ appointment.getId() +" updated");
            return (Appointment) repository.save(appointment);
        }else {
            logger.error("Appointment with ID: "+ appointment.getId() +" not found, update cancelled");
            throw new BadRequestException("Appointment not found, update cancelled");
        }
    }

    @Override
    public void deleteAppointment(Long id) throws ResourceNotFoundException {
        Optional<Appointment> appointment = findAppointment(id);
        if (appointment.isPresent()) {
            repository.deleteById(id);
            logger.info("Appointment with ID: " + id + " deleted");
        }else
            logger.error("Appointment with ID: " + id + " not exists, delete cancelled");
            throw new ResourceNotFoundException("Appointment with ID: " + id + " not exists, delete cancelled");
    }

}

