package com.dh.dentalclinic.controllers;

import com.dh.dentalclinic.entities.Dentist;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

mport java.util.List;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentists")
public class DentistController {
    @Autowired
    private DentistService service;

    @GetMapping
    public ResponseEntity<List<Dentist>> list(){
        List<Dentist> lista = service.listDentists();
        ResponseEntity response = ResponseEntity.status(HttpStatus.NO_CONTENT).build() ;
        if ( lista!= null) {
            response = ResponseEntity.ok(lista);
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> find(@PathVariable Long id){
        Optional<Dentist> dentist = service.findDentist(id);
        ResponseEntity response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (dentist.isPresent()){
            response = ResponseEntity.ok(dentist);
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<Dentist> save(@RequestBody Dentist dentist){
        return ResponseEntity.ok(service.saveDentist(dentist));
    }

    @PutMapping
    public ResponseEntity<Dentist> update(@RequestBody Dentist dentist) throws BadRequestException {
        return ResponseEntity.ok(service.updateDentist(dentist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        service.deleteDentist(id);
        return ResponseEntity.ok("Dentist deleted");
    }
}
