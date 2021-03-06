package com.dh.dentalclinic.services.impl;

import com.dh.dentalclinic.entities.AppUser;
import com.dh.dentalclinic.repositories.UserRepository;
import com.dh.dentalclinic.services.AppUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImplementation implements AppUserService {

    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = Logger.getLogger(AppUserServiceImplementation.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> userSearched = userRepository.findByEmail(username);
        if(userSearched.isPresent()){
            logger.info("Loading user");
            return userSearched.get();
        }else {
            logger.error("Invalid email");
            throw new UsernameNotFoundException("Invalid email");
        }
    }
}
