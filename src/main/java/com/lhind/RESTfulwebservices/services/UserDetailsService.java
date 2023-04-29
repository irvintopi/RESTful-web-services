package com.lhind.RESTfulwebservices.services;

import com.lhind.RESTfulwebservices.model.UserDetails;

import java.util.Optional;
public interface UserDetailsService {
     UserDetails save(UserDetails u);
     Optional<UserDetails> findById(Integer id);
}