package com.dh.dentalclinic.controllers;

import com.dh.dentalclinic.entities.Address;
import com.dh.dentalclinic.exceptions.BadRequestException;
import com.dh.dentalclinic.exceptions.ResourceNotFoundException;
import com.dh.dentalclinic.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService service;

    @GetMapping
    public ResponseEntity<List<Address>> list(){
        List<Address> list = service.listAddresses();
        ResponseEntity response = ResponseEntity.status(HttpStatus.NO_CONTENT).build() ;
        if ( list!= null) {
            response = ResponseEntity.ok(list);
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> findDomicile(@PathVariable Long id){
        Optional<Address> domicile=service.findAddress(id);
        ResponseEntity response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if(domicile.isPresent()){
            response = ResponseEntity.ok(domicile.get());
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<Address> save(@RequestBody Address address) {
        return ResponseEntity.ok(service.registerAddress(address));
    }

    @PutMapping
    public ResponseEntity<Address> update(@RequestBody Address address) throws BadRequestException {
        return ResponseEntity.ok(service.updateAddress(address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        if (service.findAddress(id).isPresent()){
            service.deleteAddress(id);
            response = ResponseEntity.ok("Patient deleted");
        }
        return response;
    }
}
