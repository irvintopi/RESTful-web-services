package com.lhind.RESTfulwebservices.services.impl;

import com.lhind.RESTfulwebservices.model.dto.BookingDTO;
import com.lhind.RESTfulwebservices.model.dto.FlightDTO;
import com.lhind.RESTfulwebservices.mapper.BookingMapper;
import com.lhind.RESTfulwebservices.mapper.FlightMapper;
import com.lhind.RESTfulwebservices.model.Booking;
import com.lhind.RESTfulwebservices.model.Flight;
import com.lhind.RESTfulwebservices.repository.BookingRepository;
import com.lhind.RESTfulwebservices.repository.FlightRepository;
import com.lhind.RESTfulwebservices.services.BookingService;
import com.lhind.RESTfulwebservices.services.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    private FlightService flightService;
    private FlightMapper flightMapper;
    private BookingMapper bookingMapper;
    private FlightRepository flightRepository;

    @Override
    public Booking save(Booking booking) {
        Booking booking1 = new Booking();
        booking1.setUser(booking.getUser());
        booking1.setBookingDate(booking.getBookingDate());
        booking1.setStatus(booking.getStatus());
        booking1.setFlights(booking.getFlights());
        booking1 = bookingRepository.save(booking1);
        return booking1;
    }

    @Override
    public Optional<Booking> findById(Integer id){
        return bookingRepository.findById(id);
    }

    @Override
    public List<BookingDTO> findAll() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(bookingMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Booking u){
        bookingRepository.delete(u);
    }

    @Override
    public List<Booking> findByFlightId(Integer flight_id) {

        List<Booking> bookings = bookingRepository.findByFlights(flightService.findById(flight_id).get());
        return bookings;
    }
    
    @Override
    public List<FlightDTO> findAllFlights(Integer id) {

        List<Flight> flights = flightRepository.findFlightsByBookings(bookingRepository.findById(id).get());
        return flights.stream().map(flightMapper::toDto).collect(Collectors.toList());
    }


}