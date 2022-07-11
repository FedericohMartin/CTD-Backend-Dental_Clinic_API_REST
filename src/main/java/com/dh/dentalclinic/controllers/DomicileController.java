package com.dh.dentalclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/domicilies")
public class DomicileController {
    @Autowired
    private DomicilieService service;

    @GetMapping
    public ResponseEntity<List<Domicilie>> list(){
        List<Domicilie> list = service.listDomicilies();
        ResponseEntity response = ResponseEntity.status(HttpStatus.NO_CONTENT).build() ;
        if ( list!= null) {
            response = ResponseEntity.ok(list);
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Domicilie> findDomicilie(@PathVariable Long id){
        Optional<Domicilie> domicile=service.findDomicilie(id);
        ResponseEntity response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if(domicile.isPresent()){
            response = ResponseEntity.ok(domicile.get());
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<Domicilie> guardar(@RequestBody Domicilie domicile) {
        return ResponseEntity.ok(service.registrarDomicilie(domicile));
    }

    @PutMapping
    public ResponseEntity<Domicilie> update(@RequestBody Domicilie domicile) throws BadRequestException {
        return ResponseEntity.ok(service.updateDomicilie(domicile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        if (service.findDomicilie(id).isPresent()){
            service.deleteDomicilie(id);
            response = ResponseEntity.ok("Patient deleted");
        }
        return response;
    }
}
