package com.lhind.RESTfulwebservices.mapper;


import com.lhind.RESTfulwebservices.model.dto.UserDTO;
import com.lhind.RESTfulwebservices.model.User;
import com.lhind.RESTfulwebservices.model.UserDetails;
import com.lhind.RESTfulwebservices.model.enums.RoleEnum;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserDTO>{

    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUserName());
        user.setPassword("1234");

        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(userDTO.getFirstName());
        userDetails.setLastName(userDTO.getLastName());
        userDetails.setPhoneNumber(userDTO.getPhoneNumber());
        userDetails.setEmail(userDTO.getEmail());

        user.setUserDetails(userDetails);
        user.setRole(RoleEnum.valueOf(userDTO.getRole()));

        return user;
    }
    @Override
    public UserDTO toDto(User u) {
        if (u ==null){
            return null;
        }
        return new UserDTO(u.getUsername(), u.getUserDetails().getFirstName(), u.getUserDetails().getLastName(), u.getRole().name() , u.getUserDetails().getPhoneNumber(), u.getUserDetails().getEmail());
    }
}