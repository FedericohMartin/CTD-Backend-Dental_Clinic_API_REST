package com.dh.dentalclinic.services;

import com.dh.dentalclinic.entities.Appointment;
import com.dh.dentalclinic.exceptions.BadRequestException;
import com.dh.dentalclinic.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<Appointment> listAppointments();

    Optional<Appointment> findAppointment(Long id);

    Appointment saveAppointment(Appointment appointment);

    Appointment updateAppointment(Appointment appointment) throws BadRequestException;

    void deleteAppointment(Long id) throws ResourceNotFoundException;
}
