package com.dh.dentalclinic.utils;

import com.dh.dentalclinic.entities.*;
import com.dh.dentalclinic.repositories.PatientRepository;
import com.dh.dentalclinic.repositories.UserRepository;
import com.dh.dentalclinic.services.AddressService;
import com.dh.dentalclinic.services.DentistService;
import com.dh.dentalclinic.services.PatientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressService addressService;
    @Autowired
    PatientService patientService;
    @Autowired
    DentistService dentistService;

    Address address1 = new Address("calle falsa", 123, "San Isidro", "Buenos Aires");

    private static final Logger logger = Logger.getLogger(DataLoader.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass1 = passwordEncoder.encode("user");
        String pass2 = passwordEncoder.encode("admin");

        userRepository.save(new AppUser("User", "user", "user@dentalclinic.com", pass1, AppUserRoles.ROLE_USER));
        logger.info("User created - Name: User , Username: user , Email: user@dentalclinic.com");

        userRepository.save(new AppUser("Admin", "admin" ,"admin@dentalclinic.com", pass2, AppUserRoles.ROLE_ADMIN));
        logger.info("User created - Name: Admin , Username: admin , Email: admin@dentalclinic.com");

        addressService.saveAddress(address1);
        logger.info("Address created");

        dentistService.saveDentist(new Dentist("Ernesto", "Muela", 00001));
        logger.info("Dentist created - Name: Ernesto , Surname: Muela , RegNumber: 00001");
    }
}
