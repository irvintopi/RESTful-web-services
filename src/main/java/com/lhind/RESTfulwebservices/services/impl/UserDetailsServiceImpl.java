package com.lhind.RESTfulwebservices.services.impl;

import com.lhind.RESTfulwebservices.model.UserDetails;
import com.lhind.RESTfulwebservices.repository.UserDetailsRepository;
import com.lhind.RESTfulwebservices.services.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    UserDetailsRepository users;
    UserDetailsServiceImpl(UserDetailsRepository users){
        this.users = users;
    }

    @Override
    public UserDetails save(UserDetails u){
        return users.save(u);
    }

    @Override
    public Optional<UserDetails> findById(Integer id) {
        return users.findById(id);
    }

    @Override
    public List<UserDetails> findFirstByFirstName(String fname){
        return users.findAllByFirstNameContainsIgnoreCase(fname);
    }

    @Override
    public List<UserDetails> findFirstByEmail(String email) {
        return users.findAllByEmailContainsIgnoreCase(email);
    }

    @Override
    public List<UserDetails> findFirstByPhoneNumber(String phone){
        return users.findAllByPhoneNumberContainsIgnoreCase(phone);
    }
}