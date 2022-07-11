package com.dh.dentalclinic.controllers;

import com.dh.dentalclinic.entities.Domicile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/domiciles")
public class DomicileController {
    @Autowired
    private DomicileService service;

    @GetMapping
    public ResponseEntity<List<Domicile>> list(){
        List<Domicile> list = service.listDomiciles();
        ResponseEntity response = ResponseEntity.status(HttpStatus.NO_CONTENT).build() ;
        if ( list!= null) {
            response = ResponseEntity.ok(list);
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Domicile> findDomicile(@PathVariable Long id){
        Optional<Domicile> domicile=service.findDomicile(id);
        ResponseEntity response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if(domicile.isPresent()){
            response = ResponseEntity.ok(domicile.get());
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<Domicile> guardar(@RequestBody Domicile domicile) {
        return ResponseEntity.ok(service.registrarDomicile(domicile));
    }

    @PutMapping
    public ResponseEntity<Domicile> update(@RequestBody Domicile domicile) throws BadRequestException {
        return ResponseEntity.ok(service.updateDomicile(domicile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        if (service.findDomicile(id).isPresent()){
            service.deleteDomicile(id);
            response = ResponseEntity.ok("Patient deleted");
        }
        return response;
    }
}
