package com.dh.dentalclinic.utils;

import com.dh.dentalclinic.entities.AppUser;
import com.dh.dentalclinic.entities.AppUserRoles;
import com.dh.dentalclinic.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader  implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;
    private static final Logger logger = Logger.getLogger(DataLoader.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String pass1 = passwordEncoder.encode("user");
        String pass2 = passwordEncoder.encode("admin");

        userRepository.save(new AppUser("User", "user", "user@dentalclinic.com", pass1, AppUserRoles.ROLE_USER ));
        logger.info("User created - Name: User , Username: user , Email: user@dentalclinic.com");

        userRepository.save(new AppUser("Admin", "admin" ,"admin@dentalclinic.com", pass2, AppUserRoles.ROLE_ADMIN));
        logger.info("User created - Name: Admin , Username: admin , Email: admin@dentalclinic.com");
    }
}
