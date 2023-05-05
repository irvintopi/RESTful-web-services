package com.lhind.RESTfulwebservices.services.impl;

import com.lhind.RESTfulwebservices.model.UserDetails;
import com.lhind.RESTfulwebservices.repository.UserDetailsRepository;
import com.lhind.RESTfulwebservices.services.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    UserDetailsRepository users;


    public UserDetails save(UserDetails u){
        return users.save(u);
    }

    public Optional<UserDetails> findById(Integer id) {
        return users.findById(id);
    }
}