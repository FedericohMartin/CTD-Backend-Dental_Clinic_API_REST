package com.dh.dentalclinic.services;

import com.dh.dentalclinic.entities.Address;
import com.dh.dentalclinic.exceptions.BadRequestException;
import com.dh.dentalclinic.exceptions.ResourceNotFoundException;
import com.dh.dentalclinic.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository repository;

    public Optional<Address> findAddress(Long id){
        return repository.findById(id);
    }

    public List<Address> listAddresses(){
        return repository.findAll();
    }

    public Address registerAddress(Address address){
        return (Address) repository.save(address);
    }

    public void deleteAddress(Long id)throws ResourceNotFoundException {
        Optional<Address> address = findAddress(id);
        if (address.isPresent())
            repository.deleteById(id);
        else
            throw new ResourceNotFoundException("Address with id: " + id + " not found, delete cancelled");
    }

    public Address updateAddress(Address address) throws BadRequestException {
        if (findAddress(address.getId()).isPresent()){
            return (Address) repository.save(address);
        }
        else {
            throw new BadRequestException("Address not found, update cancelled");
        }
    }
}

