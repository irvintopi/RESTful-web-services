package com.lhind.RESTfulwebservices.Controller;

import com.lhind.RESTfulwebservices.dto.UserDTO;
import com.lhind.RESTfulwebservices.model.Booking;
import com.lhind.RESTfulwebservices.model.User;
import com.lhind.RESTfulwebservices.model.UserDetails;
import com.lhind.RESTfulwebservices.services.BookingService;
import com.lhind.RESTfulwebservices.services.UserDetailsService;
import com.lhind.RESTfulwebservices.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;
    UserDetailsService userDetailsService;

    BookingService bookingService;

    UserController(UserService userService, UserDetailsService userDetailsService, BookingService bookingService){
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.bookingService= bookingService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDTO> get(){
        return userService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) {
        User user1 = userService.findById(id);
        if (user1 != null) {
            UserDTO userDTO = userService.converter(user1);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "/flights/{flightId}")
    public List<UserDTO> getUserByFlightId(@PathVariable Integer flightId) {
        List<Booking> bookings = bookingService.findByFlightId(flightId);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (Booking booking : bookings) {
            User user = userService.findById(booking.getUser().getId());
            UserDTO userDto = userService.converter(user);
            userDTOs.add(userDto);
        }
        return userDTOs;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.delete(userService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/add"})
    public User createUser(@RequestBody User user) {
        User user1= new User();
        user1.setUserName(user.getUserName());
        user1.setPassword(user.getPassword());
        user1.setRole(user.getRole());

        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(user.getUserDetails().getFirstName());
        userDetails.setLastName(user.getUserDetails().getLastName());
        userDetails.setEmail(user.getUserDetails().getEmail());
        userDetails.setPhoneNumber(user.getUserDetails().getPhoneNumber());
        userDetails.setUser(user1);

        userService.save(user1);
        userDetailsService.save(userDetails);

        return user1;
    }
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        User userToUpdate = userService.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        userToUpdate.setUserName(user.getUserName());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setRole(user.getRole());

        UserDetails userDetails = userToUpdate.getUserDetails();
        userDetails.setFirstName(user.getUserDetails().getFirstName());
        userDetails.setLastName(user.getUserDetails().getLastName());
        userDetails.setEmail(user.getUserDetails().getEmail());
        userDetails.setPhoneNumber(user.getUserDetails().getPhoneNumber());

        userService.save(userToUpdate);

        return userToUpdate;
    }


}
