package com.lhind.RESTfulwebservices.repository;
import com.lhind.RESTfulwebservices.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
    public List<UserDetails> findAllByFirstNameContainsIgnoreCase(String fname);
    public List<UserDetails> findAllByEmailContainsIgnoreCase(String email);
    public List<UserDetails> findAllByPhoneNumberContainsIgnoreCase(String phone);
}