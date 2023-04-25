package com.lhind.RESTfulwebservices.services.impl;

import com.lhind.RESTfulwebservices.dto.BookingDTO;
import com.lhind.RESTfulwebservices.dto.UserDTO;
import com.lhind.RESTfulwebservices.model.Booking;
import com.lhind.RESTfulwebservices.model.User;
import com.lhind.RESTfulwebservices.model.UserDetails;
import com.lhind.RESTfulwebservices.repository.UserRepository;
import com.lhind.RESTfulwebservices.services.BookingService;
import com.lhind.RESTfulwebservices.services.UserService;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private BookingService bookingService;

    UserServiceImpl(UserRepository userRepository, BookingService bookingService) {
        this.userRepository = userRepository;
        this.bookingService = bookingService;
    }

    @Override
    public User save(User u) {
        User user = userRepository.save(u);
        return user;
    }

    @Override
    public User findById(Integer id) {
        Optional <User> user = userRepository.findById(id);
        if (user.isPresent()){
            User user1 = user.get();
            return user1;
        }
        else {
            return null;
        }
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> userDTOs = userRepository.findAll().stream().map(this::converter).collect(Collectors.toList());
        return userDTOs;
    }


    @Override
    public void delete(User u) {
        userRepository.delete(u);
    }

    @Override
    public UserDTO converter(User u) {
        if (u == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO(u.getUserName(), u.getUserDetails().getFirstName(), u.getUserDetails().getLastName(), u.getRole() , u.getUserDetails().getPhoneNumber(), u.getUserDetails().getEmail());
        return userDTO;
    }

    @Override
    public UserDTO findUserByBookings(Booking b) {
        User user = userRepository.findUserByBookings(b);
        return converter(user);
    }

    @Override
    public UserDTO findAllByUserDetails(UserDetails ud) {
        User user = userRepository.findAllByUserDetails(ud);
        return converter(user);
    }

    @Override
    public List<BookingDTO> findAllBookings(Integer id) {
        List<Integer> bookingIds = userRepository.findAllBookingsOfAUser(id);
        List<BookingDTO> bookingDtoList = new ArrayList<>();
        for (Integer i:bookingIds) {
            Optional<Booking> bookingOptional = bookingService.findById(i);

            if (bookingOptional.isPresent()) {
                BookingDTO bookingDto = bookingService.converter(bookingOptional.get());
                bookingDtoList.add(bookingDto);
            }
        }
        return bookingDtoList;
    }



}
