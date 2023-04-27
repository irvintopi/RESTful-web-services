package com.lhind.RESTfulwebservices.services;

import com.lhind.RESTfulwebservices.dto.BookingDTO;
import com.lhind.RESTfulwebservices.dto.UserDTO;
import com.lhind.RESTfulwebservices.model.Booking;
import com.lhind.RESTfulwebservices.model.User;
import com.lhind.RESTfulwebservices.model.UserDetails;

import java.util.List;

public interface UserService {
    User save(User u);
    User update(Integer id, User updatedUser);
    User findById(Integer id);
    List<UserDTO> findAll();
    void delete(User u);
    UserDTO converter(User u);
    List<BookingDTO> findAllBookings(Integer id);
    BookingDTO findBookingByIdAndUser(Integer bookingId, Integer id);


}