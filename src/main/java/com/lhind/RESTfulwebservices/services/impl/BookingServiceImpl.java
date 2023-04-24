package com.lhind.RESTfulwebservices.services.impl;

import com.lhind.RESTfulwebservices.dto.BookingDTO;
import com.lhind.RESTfulwebservices.model.Booking;
import com.lhind.RESTfulwebservices.repository.BookingRepository;
import com.lhind.RESTfulwebservices.services.BookingService;
import com.lhind.RESTfulwebservices.services.FlightService;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    private FlightService flightService;

    public BookingServiceImpl(BookingRepository bookingRepository, FlightService flightService) {
        this.bookingRepository = bookingRepository;
        this.flightService = flightService;
    }

    public Booking save(Booking b){
        return bookingRepository.save(b);
    }
    public Optional<Booking> findById(Integer id){
        return bookingRepository.findById(id);
    }
    public List<Booking> findAll(){
        return bookingRepository.findAll();
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
    public List<Integer> findAllFlights(Integer id) {
        return bookingRepository.findAllById(id);
    }
}