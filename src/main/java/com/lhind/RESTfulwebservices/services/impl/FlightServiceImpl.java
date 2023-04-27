package com.lhind.RESTfulwebservices.services.impl;

import com.lhind.RESTfulwebservices.dto.FlightDTO;
import com.lhind.RESTfulwebservices.model.Flight;
import com.lhind.RESTfulwebservices.repository.FlightRepository;
import com.lhind.RESTfulwebservices.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;
    private FlightDTO flightDTO;

    FlightServiceImpl(FlightRepository flightRepository, FlightDTO flightDTO){
        this.flightRepository = flightRepository;
        this.flightDTO = flightDTO;
    }

    public Flight save(Flight f) { return flightRepository.save(f); }

    public Optional<Flight> findById(Integer id) {
        return flightRepository.findById(id);
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public void delete(Flight f) { flightRepository.delete(f); }



    @Override
    public List<Integer> findAllBookings(Integer id) {
        return null;
    }

    @Override
    public List<Integer> findAllUsers(int id) {
        return null;
    }
}

