package com.lhind.RESTfulwebservices.services;

import com.lhind.RESTfulwebservices.dto.BookingDTO;
import com.lhind.RESTfulwebservices.dto.FlightDTO;
import com.lhind.RESTfulwebservices.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {
   Booking save(Booking b);
   Optional<Booking> findById(Integer id);
   List<BookingDTO> findAll();
   void delete(Booking u);
   List<Booking> findByFlightId(Integer flight_id);
   List<FlightDTO> findAllFlights(Integer id);

}