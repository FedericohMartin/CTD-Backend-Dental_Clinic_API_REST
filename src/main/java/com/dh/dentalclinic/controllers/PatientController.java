package com.dh.dentalclinic.controllers;

import com.dh.dentalclinic.entities.Patient;
import com.dh.dentalclinic.exceptions.BadRequestException;
import com.dh.dentalclinic.exceptions.ResourceNotFoundException;
import com.dh.dentalclinic.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService service;

    @GetMapping
    public ResponseEntity<List<Patient>> list() {
        List<Patient> list = service.listPatients();
        ResponseEntity response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        if (list != null) {
            response = ResponseEntity.ok(list);
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findPatient(@PathVariable Long id) {
        Optional<Patient> patientSearched = service.findPatient(id);
        if (patientSearched.isPresent()) {
            return ResponseEntity.ok(patientSearched.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/email")
    public ResponseEntity<Patient> find(@RequestParam String email) throws ResourceNotFoundException {
        Optional<Patient> patient = service.findByEmail(email);
        return ResponseEntity.ok(patient.get());
    }

    @PostMapping
    public ResponseEntity<Patient> save(@RequestBody Patient patient) {
        return ResponseEntity.ok(service.savePatient(patient));
    }

    @PutMapping
    public ResponseEntity<Patient> update(@RequestBody Patient patient) throws BadRequestException {
        return ResponseEntity.ok(service.updatePatient(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        service.deletePatient(id);
        return ResponseEntity.ok("Patient deleted");
    }
}
