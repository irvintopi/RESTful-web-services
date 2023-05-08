package com.lhind.RESTfulwebservices.authentication;

import com.lhind.RESTfulwebservices.config.TokenService;
import com.lhind.RESTfulwebservices.model.User;
import com.lhind.RESTfulwebservices.model.UserDetails;
import com.lhind.RESTfulwebservices.repository.UserDetailsRepository;
import com.lhind.RESTfulwebservices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDetailsService userDetailsService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final TokenService jwtService;

    private final UserDetailsRepository detailsRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        final AuthenticationResponse authenticationResponse = new AuthenticationResponse();

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
            authenticationResponse.setToken(tokenService.generateToken(userDetailsService.loadUserByUsername(authenticationRequest.getUsername())));

        return authenticationResponse;
    }

    public AuthenticationResponse register(RegisterRequest request) {

        User user1 = new User();
        BeanUtils.copyProperties(request, user1);

        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(request.getFirstName());
        userDetails.setLastName(request.getLastName());
        userDetails.setEmail(request.getEmail());
        userDetails.setPhoneNumber(request.getPhoneNumber());
        userDetails.setUser(user1);

        user1.setPassword(passwordEncoder.encode(request.getPassword()));

        user1.setUserDetails(userDetails);
        repository.save(user1);
        detailsRepository.save(userDetails);


        var jwtToken =  jwtService.generateToken(user1);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
