package com.lhind.RESTfulwebservices.services;

import com.lhind.RESTfulwebservices.model.dto.BookingDTO;
import com.lhind.RESTfulwebservices.model.dto.UserDTO;
import com.lhind.RESTfulwebservices.model.User;

import java.util.List;

public interface UserService {
    User save(User u);
    User update(Integer id, User updatedUser);

    List<UserDTO> findUsersOnFlight(Integer flightId);
    List<UserDTO> findAll();
    void delete(Integer id);
    List<BookingDTO> findAllBookings(Integer id);
    BookingDTO findBookingByIdAndUser(Integer bookingId, Integer id);
    UserDTO findById(Integer id);
}