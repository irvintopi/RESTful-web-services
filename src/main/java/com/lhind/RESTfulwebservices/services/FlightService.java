package com.lhind.RESTfulwebservices.services;

import com.lhind.RESTfulwebservices.dto.FlightDTO;
import com.lhind.RESTfulwebservices.model.Flight;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FlightService {
    FlightDTO save(Flight f);
    Optional<Flight> findById(Integer id);
    List<FlightDTO> findAll();
    void delete(Flight f);
    List<Integer> findAllBookings(Integer id);
    List<Integer> findAllUsers(int id);

    Flight findByDateAndAirport(Date date, String airport);
}