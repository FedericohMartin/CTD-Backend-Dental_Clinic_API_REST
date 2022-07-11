package com.dh.dentalclinic.services;

import com.dh.dentalclinic.entities.Address;
import com.dh.dentalclinic.exceptions.BadRequestException;
import com.dh.dentalclinic.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Optional<Address> findAddress(Long id);

    List<Address> listAddresses();

    Address saveAddress(Address address);

    void deleteAddress(Long id) throws ResourceNotFoundException;

    Address updateAddress(Address address) throws BadRequestException;
}
