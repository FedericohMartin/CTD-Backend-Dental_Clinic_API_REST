package com.dh.dentalclinic.controllers;

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
    public ResponseEntity<Appointment> buscar(@PathVariable Long id){
        Optional<Appointment> appointment = service.buscarAppointment(id);
        ResponseEntity response = ResponseEntity.notFound().build();
        if (appointment.isPresent()){
            response = ResponseEntity.ok(appointment);
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<Appointment> guardar(@RequestBody Appointment appointment){
        ResponseEntity<Appointment> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Optional<Paciente> pacienteBus = pacienteService.buscarPaciente(appointment.getPaciente().getId());
        Optional<Odontologo> odontologoBus = odontologoService.buscarOdontologo(appointment.getPaciente().getId());
        if (pacienteBus.isPresent() && odontologoBus.isPresent()){
            response = ResponseEntity.ok(service.guardarAppointment(appointment));
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<Appointment> actualizar(@RequestBody Appointment appointment) throws BadRequestException {
        Appointment appointmentActualizado = service.actualizarAppointment(appointment);
        return ResponseEntity.ok(appointmentActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws ResourceNotFoundException {
        service.eliminarAppointment(id);
        return ResponseEntity.ok("Appointment eliminado");
    }
}
