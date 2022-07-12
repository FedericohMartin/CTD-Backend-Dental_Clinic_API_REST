package com.dh.dentalclinic.services.impl;

import com.dh.dentalclinic.entities.Address;
import com.dh.dentalclinic.exceptions.BadRequestException;
import com.dh.dentalclinic.exceptions.ResourceNotFoundException;
import com.dh.dentalclinic.repositories.AddressRepository;
import com.dh.dentalclinic.services.AddressService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImplementation implements AddressService {
    @Autowired
    private AddressRepository repository;
    private static final Logger logger = Logger.getLogger(AddressServiceImplementation.class);

    @Override
    public Optional<Address> findAddress(Long id){
        logger.info("Finding address with ID :"+ id);
        return repository.findById(id);
    }

    @Override
    public List<Address> listAddresses(){
        logger.info("Listing all addresses");
        return repository.findAll();
    }

    @Override
    public Address saveAddress(Address address){
        logger.info("Saving new address");
        return (Address) repository.save(address);
    }

    @Override
    public void deleteAddress(Long id)throws ResourceNotFoundException {
        Optional<Address> address = findAddress(id);
        if (address.isPresent()) {
            repository.deleteById(id);
            logger.info("Address with ID: " + id + " deleted");
        }else
            logger.error("Address with ID: " + id + " not found, delete cancelled");
            throw new ResourceNotFoundException("Address with ID: " + id + " not found, delete cancelled");
    }

    @Override
    public Address updateAddress(Address address) throws BadRequestException {
        if (findAddress(address.getId()).isPresent()){
            logger.info("Address with ID: "+ address.getId() +" updated");
            return (Address) repository.save(address);
        }else {
            logger.error("Address with ID: "+ address.getId() +"not found, update cancelled");
            throw new BadRequestException("Address not found, update cancelled");
        }
    }
}

