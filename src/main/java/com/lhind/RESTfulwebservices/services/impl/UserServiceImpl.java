package com.lhind.RESTfulwebservices.services.impl;

import com.lhind.RESTfulwebservices.model.dto.BookingDTO;
import com.lhind.RESTfulwebservices.model.dto.UserDTO;
import com.lhind.RESTfulwebservices.mapper.BookingMapper;
import com.lhind.RESTfulwebservices.mapper.UserMapper;
import com.lhind.RESTfulwebservices.model.Booking;
import com.lhind.RESTfulwebservices.model.User;
import com.lhind.RESTfulwebservices.model.UserDetails;
import com.lhind.RESTfulwebservices.repository.BookingRepository;
import com.lhind.RESTfulwebservices.repository.UserDetailsRepository;
import com.lhind.RESTfulwebservices.repository.UserRepository;
import com.lhind.RESTfulwebservices.services.BookingService;
import com.lhind.RESTfulwebservices.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BookingService bookingService;
    private UserDetailsRepository userDetailsRepository;
    private UserMapper userMapper;
    private BookingMapper bookingMapper;
    private BookingRepository bookingRepository;

    @Override
    public User save(User user) {
        User user1 = new User();
        BeanUtils.copyProperties(user, user1);

        UserDetails userDetails = new UserDetails();
        BeanUtils.copyProperties(user.getUserDetails(), userDetails, "userDetails");
        userDetails.setUser(user1);

        // Hash the user's password using bcrypt
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user1.setPassword(encodedPassword);

        user1.setUserDetails(userDetails);
        userRepository.save(user1);
        userDetailsRepository.save(userDetails);

        return user1;
    }

    @Override
    public User update(Integer id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    BeanUtils.copyProperties(updatedUser, user, "id", "userDetails");
                    BeanUtils.copyProperties(updatedUser.getUserDetails(), user.getUserDetails());
                    user.getUserDetails().setUser(user);

                    return userRepository.save(user);
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public List<UserDTO> findUsersOnFlight(Integer flightId) {
        List<Booking> bookings = bookingService.findByFlightId(flightId);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (Booking booking : bookings) {
            User user = userRepository.findById(booking.getUser().getId()).get();
            UserDTO userDto = userMapper.toDto(user);
            userDTOs.add(userDto);
        }
        return userDTOs;
    }

    @Override
    public UserDTO findById(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> authenticatedUser = userRepository.findByUsername(authentication.getName());

        if (authenticatedUser.isPresent() && (authenticatedUser.get().getId().equals(id) || authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")))) {
            return userMapper.toDto(userRepository.findById(id).get());
        }

        return null;
    }


    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }


    @Override
    public void delete(Integer u) {
        userRepository.delete(userRepository.findById(u).get());
    }

    @Override
    public BookingDTO findBookingByIdAndUser(Integer bookingId, Integer id) {
        Booking booking = bookingRepository.findByIdAndUserId(bookingId, id);
        return bookingMapper.toDto(booking);

    }

    @Override
    public List<BookingDTO> findAllBookings(Integer id) {

        List<Booking> bookings = bookingRepository.findAllByUserId(id);
        return bookings.stream().map(bookingMapper::toDto).collect(Collectors.toList());
    }
}
