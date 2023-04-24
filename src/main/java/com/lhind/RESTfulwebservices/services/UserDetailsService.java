package com.lhind.RESTfulwebservices.services;

import com.lhind.RESTfulwebservices.model.UserDetails;

import java.util.List;
import java.util.Optional;
public interface UserDetailsService {
    public UserDetails save(UserDetails u);
    public Optional<UserDetails> findById(Integer id);
    public List<UserDetails> findFirstByFirstName(String fname);
    public List<UserDetails> findFirstByEmail(String email);
    public List<UserDetails> findFirstByPhoneNumber(String phone);

}