package com.lhind.RESTfulwebservices.Controller;

import com.lhind.RESTfulwebservices.dto.BookingDTO;
import com.lhind.RESTfulwebservices.dto.UserDTO;
import com.lhind.RESTfulwebservices.mapper.UserMapper;
import com.lhind.RESTfulwebservices.model.Booking;
import com.lhind.RESTfulwebservices.model.User;
import com.lhind.RESTfulwebservices.model.UserDetails;
import com.lhind.RESTfulwebservices.services.BookingService;
import com.lhind.RESTfulwebservices.services.UserDetailsService;
import com.lhind.RESTfulwebservices.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;
    UserDetailsService userDetailsService;

    BookingService bookingService;

    UserMapper userMapper;

    UserController(UserService userService, UserDetailsService userDetailsService, BookingService bookingService, UserMapper userMapper){
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.bookingService= bookingService;
        this.userMapper= userMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDTO> get(){
        return userService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) {
        User user1 = userService.findById(id);
        if (user1 != null) {
            UserDTO userDTO = userMapper.toDto(user1);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/flights/{flightId}")
    public List<UserDTO> getUsersByFlightId(@PathVariable Integer flightId) {
        List<Booking> bookings = bookingService.findByFlightId(flightId);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (Booking booking : bookings) {
            User user = userService.findById(booking.getUser().getId());
            UserDTO userDto = userMapper.toDto(user);
            userDTOs.add(userDto);
        }
        return userDTOs;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.delete(userService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/add"})
    public UserDTO createUser(@RequestBody User user) {
        userService.save(user);
        return userMapper.toDto(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
    public UserDTO updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        User savedUser = userService.update(id, updatedUser);
        return userMapper.toDto(savedUser);
    }



}
