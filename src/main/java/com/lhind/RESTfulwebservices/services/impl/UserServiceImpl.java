package com.lhind.RESTfulwebservices.services.impl;

import com.lhind.RESTfulwebservices.dto.BookingDTO;
import com.lhind.RESTfulwebservices.dto.UserDTO;
import com.lhind.RESTfulwebservices.mapper.BookingMapper;
import com.lhind.RESTfulwebservices.mapper.UserMapper;
import com.lhind.RESTfulwebservices.model.Booking;
import com.lhind.RESTfulwebservices.model.User;
import com.lhind.RESTfulwebservices.model.UserDetails;
import com.lhind.RESTfulwebservices.repository.UserDetailsRepository;
import com.lhind.RESTfulwebservices.repository.UserRepository;
import com.lhind.RESTfulwebservices.services.BookingService;
import com.lhind.RESTfulwebservices.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private BookingService bookingService;

    private UserDetailsRepository userDetailsRepository;

    private UserMapper userMapper;
    private BookingMapper bookingMapper;

    UserServiceImpl(UserRepository userRepository, BookingService bookingService, UserDetailsRepository userDetailsRepository, UserMapper userMapper, BookingMapper bookingMapper) {
        this.userRepository = userRepository;
        this.bookingService = bookingService;
        this.userDetailsRepository = userDetailsRepository;
        this.userMapper = userMapper;
        this.bookingMapper= bookingMapper;
    }

    @Override
    public User save(User user) {
        User user1 = new User();
        BeanUtils.copyProperties(user, user1);

        UserDetails userDetails = new UserDetails();
        BeanUtils.copyProperties(user.getUserDetails(), userDetails, "userDetails");
        userDetails.setUser(user1);

        user1.setUserDetails(userDetails);
        userRepository.save(user1);
        userDetailsRepository.save(userDetails);

        return user1;
    }

    @Override
    public User update(Integer id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    BeanUtils.copyProperties(updatedUser, user,"id", "userDetails");
                    BeanUtils.copyProperties(updatedUser.getUserDetails(), user.getUserDetails());
                    user.getUserDetails().setUser(user);

                    return userRepository.save(user);
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
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
        return userMapper.toDto(u);
    }

    @Override
    public UserDTO findUserByBookings(Booking b) {
        User user = userRepository.findUserByBookings(b);
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO findAllByUserDetails(UserDetails ud) {
        User user = userRepository.findAllByUserDetails(ud);
        return userMapper.toDto(user);
    }
    @Override
    public BookingDTO findBookingByIdAndUser(Integer bookingId, Integer id){
        Optional<Booking> booking = bookingService.findById(userRepository.findBookingByIdAndUser(bookingId, id));
        if (booking.isPresent()){
            BookingDTO bookingDTO = bookingMapper.toDto(booking.get());
            return bookingDTO;
        }else return null;
    }
    @Override
    public List<BookingDTO> findAllBookings(Integer id) {
        List<Integer> bookingIds = userRepository.findAllBookingsOfAUser(id);
        List<BookingDTO> bookingDtoList = new ArrayList<>();
        for (Integer i:bookingIds) {
            Optional<Booking> bookingOptional = bookingService.findById(i);

            if (bookingOptional.isPresent()) {
                BookingDTO bookingDto = bookingMapper.toDto(bookingOptional.get());
                bookingDtoList.add(bookingDto);
            }
        }
        return bookingDtoList;
    }



}
