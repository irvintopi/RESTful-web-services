package com.lhind.RESTfulwebservices.services;

import com.lhind.RESTfulwebservices.dto.FlightDTO;
import com.lhind.RESTfulwebservices.model.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    public Flight save(Flight f);
    public Optional<Flight> findById(Integer id);
    public List<Flight> findAll();
    public void delete(Flight f);
    List<Integer> findAllBookings(Integer id);
    public List<Integer> findAllUsers(int id);

}