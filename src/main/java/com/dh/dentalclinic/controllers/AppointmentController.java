package com.dh.dentalclinic.controllers;

import com.dh.dentalclinic.entities.Appointment;
import com.dh.dentalclinic.entities.Dentist;
import com.dh.dentalclinic.entities.Patient;
import com.dh.dentalclinic.exceptions.BadRequestException;
import com.dh.dentalclinic.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService service;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DentistService dentistService;


    @GetMapping
    public ResponseEntity<List<Appointment>> list(){
        List<Appointment> list = service.listAppointments();
        ResponseEntity response = ResponseEntity.status(HttpStatus.NO_CONTENT).build() ;
        if ( list!= null) {
            response = ResponseEntity.ok(list);
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> find(@PathVariable Long id){
        Optional<Appointment> appointment = service.findAppointment(id);
        ResponseEntity response = ResponseEntity.notFound().build();
        if (appointment.isPresent()){
            response = ResponseEntity.ok(appointment);
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<Appointment> save(@RequestBody Appointment appointment){
        ResponseEntity<Appointment> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Optional<Patient> patientBus = patientService.findPatient(appointment.getPatient().getId());
        Optional<Dentist> dentistBus = dentistService.findDentist(appointment.getPatient().getId());
        if (patientBus.isPresent() && dentistBus.isPresent()){
            response = ResponseEntity.ok(service.saveAppointment(appointment));
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<Appointment> update(@RequestBody Appointment appointment) throws BadRequestException {
        Appointment appointmentUpdated = service.updateAppointment(appointment);
        return ResponseEntity.ok(appointmentUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        service.deleteAppointment(id);
        return ResponseEntity.ok("Appointment deleted");
    }
}
