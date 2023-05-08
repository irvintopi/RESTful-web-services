package com.lhind.RESTfulwebservices.authentication;

import com.lhind.RESTfulwebservices.model.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private RoleEnum role;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}

