package com.lhind.RESTfulwebservices.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String role;
    private String phoneNumber;
    private String email;
}