package com.lhind.RESTfulwebservices.services;

import com.lhind.RESTfulwebservices.dto.UserDTO;
import com.lhind.RESTfulwebservices.model.Booking;
import com.lhind.RESTfulwebservices.model.User;
import com.lhind.RESTfulwebservices.model.UserDetails;

import java.util.List;

public interface UserService {
    User save(User u);
    User findById(Integer id);
    List<UserDTO> findAll();
    void delete(User u);
    UserDTO converter(User u);
    UserDTO findUserByBookings(Booking b);

    UserDTO findAllByUserDetails(UserDetails ud);
    List<Integer> findAllBookings(Integer id);
    List<Integer> findAllFlights(int id);


}