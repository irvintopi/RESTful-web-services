package com.lhind.RESTfulwebservices.services;

import com.lhind.RESTfulwebservices.dto.FlightDTO;
import com.lhind.RESTfulwebservices.model.Flight;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FlightService {
    FlightDTO save(Flight f);
    Optional<Flight> findById(Integer id);
    List<FlightDTO> findAll();
    void delete(Flight f);
    ResponseEntity<FlightDTO> findByDateAndAirport(Date date, String airport);
}