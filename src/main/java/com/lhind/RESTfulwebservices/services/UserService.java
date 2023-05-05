package com.lhind.RESTfulwebservices.services;

import com.lhind.RESTfulwebservices.model.dto.BookingDTO;
import com.lhind.RESTfulwebservices.model.dto.UserDTO;
import com.lhind.RESTfulwebservices.model.User;

import java.util.List;

public interface UserService {
    User save(User u);
    User update(Integer id, User updatedUser);

    List<UserDTO> findUsersOnFlight(Integer flightId);

    User findById(Integer id);
    List<UserDTO> findAll();
    void delete(User u);
    List<BookingDTO> findAllBookings(Integer id);
    BookingDTO findBookingByIdAndUser(Integer bookingId, Integer id);


}