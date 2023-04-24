package com.lhind.RESTfulwebservices.Controller;

import com.lhind.RESTfulwebservices.dto.FlightDTO;
import com.lhind.RESTfulwebservices.dto.UserDTO;
import com.lhind.RESTfulwebservices.model.User;
import com.lhind.RESTfulwebservices.services.UserDetailsService;
import com.lhind.RESTfulwebservices.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;
    UserDetailsService userDetailsService;

    UserController(UserService userService, UserDetailsService userDetailsService){
        this.userService = userService;
        this.userDetailsService = userDetailsService;
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
    


}
