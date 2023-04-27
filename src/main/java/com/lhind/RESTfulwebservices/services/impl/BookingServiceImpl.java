package com.lhind.RESTfulwebservices.services.impl;

import com.lhind.RESTfulwebservices.dto.BookingDTO;
import com.lhind.RESTfulwebservices.dto.FlightDTO;
import com.lhind.RESTfulwebservices.mapper.FlightMapper;
import com.lhind.RESTfulwebservices.model.Booking;
import com.lhind.RESTfulwebservices.model.Flight;
import com.lhind.RESTfulwebservices.model.User;
import com.lhind.RESTfulwebservices.model.UserDetails;
import com.lhind.RESTfulwebservices.repository.BookingRepository;
import com.lhind.RESTfulwebservices.repository.UserRepository;
import com.lhind.RESTfulwebservices.services.BookingService;
import com.lhind.RESTfulwebservices.services.FlightService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    private FlightService flightService;
    private UserRepository userRepository;

    private FlightMapper flightMapper;
    public BookingServiceImpl(BookingRepository bookingRepository, FlightService flightService,UserRepository userRepository, FlightMapper flightMapper) {
        this.bookingRepository = bookingRepository;
        this.flightService = flightService;
        this.userRepository = userRepository;
        this.flightMapper= flightMapper;
    }

    public Booking save(Booking booking) {
        Booking booking1 = new Booking();
        booking1.setUser(booking.getUser());
        booking1.setBookingDate(booking.getBookingDate());
        booking1.setStatus(booking.getStatus());
        booking1.setFlights(booking.getFlights());
        booking1 = bookingRepository.save(booking1);
        return booking1;
    }
    public Optional<Booking> findById(Integer id){
        return bookingRepository.findById(id);
    }
    public List<BookingDTO> findAll(){
        return bookingRepository.findAll().stream().map(this::converter).collect(Collectors.toList());
    }
    public void delete(Booking u){
        bookingRepository.delete(u);
    }

    @Override
    public BookingDTO converter(Booking b) {
        if (b == null) {
            return null;
        }

        return new BookingDTO(b.getUser().getUserName(), b.getStatus(), b.getBookingDate());
    }
    @Override
    public List<Booking> findByFlightId(Integer flight_id) {
        return bookingRepository.findByFlightId(flight_id);
    }
    
    @Override
    public List<FlightDTO> findAllFlights(Integer id) {
        List<Integer> flightIds = bookingRepository.findAllById(id);
        List<FlightDTO> flights = new ArrayList<>();

        for (Integer flightId : flightIds) {
            flightService.findById(flightId).ifPresent(flight -> flights.add(flightMapper.toDto(flight)));
        }

        return flights;
    }


}