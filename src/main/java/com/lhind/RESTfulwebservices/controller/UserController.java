package com.lhind.RESTfulwebservices.controller;

import com.lhind.RESTfulwebservices.model.dto.UserDTO;
import com.lhind.RESTfulwebservices.mapper.UserMapper;
import com.lhind.RESTfulwebservices.model.User;
import com.lhind.RESTfulwebservices.services.BookingService;
import com.lhind.RESTfulwebservices.services.UserDetailsService;
import com.lhind.RESTfulwebservices.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    UserService userService;
    UserDetailsService userDetailsService;
    BookingService bookingService;
    UserMapper userMapper;

    //get all users and details
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> get(){
        return ResponseEntity.ok(userService.findAll());
    }

    //get user and details by id
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

    //get users on a specific flight
    @RequestMapping(method = RequestMethod.GET, value = "/flights/{flightId}")
    public ResponseEntity<List<UserDTO>> getUsersByFlightId(@PathVariable Integer flightId) {
        return ResponseEntity.ok(userService.findUsersOnFlight(flightId));
    }

    //add new User
    @RequestMapping(method = RequestMethod.POST, value = {"/add"})
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    //update user
    @RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        User savedUser = userService.update(id, updatedUser);
        return ResponseEntity.ok(userMapper.toDto(savedUser));
    }

    //delete user
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.delete(userService.findById(id));
    }
}
