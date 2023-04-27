package com.lhind.RESTfulwebservices.services.impl;

import com.lhind.RESTfulwebservices.dto.FlightDTO;
import com.lhind.RESTfulwebservices.mapper.FlightMapper;
import com.lhind.RESTfulwebservices.model.Flight;
import com.lhind.RESTfulwebservices.repository.FlightRepository;
import com.lhind.RESTfulwebservices.services.FlightService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;

    private FlightMapper flightMapper;

    FlightServiceImpl(FlightRepository flightRepository, FlightMapper flightMapper){
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @Override
    public FlightDTO save(Flight f) {
        flightRepository.save(f);
        return flightMapper.toDto(f);
    }

    @Override
    public Optional<Flight> findById(Integer id) {
        return flightRepository.findById(id);
    }

    @Override
    public List<FlightDTO> findAll() {
        List<Flight> flights = flightRepository.findAll();
        List<FlightDTO> flightDTOs = new ArrayList<>();
        for (Flight flight : flights) {
            flightDTOs.add(flightMapper.toDto(flight));
        }
        return flightDTOs;
    }

    @Override
    public void delete(Flight f) { flightRepository.delete(f); }

    @Override
    public List<Integer> findAllBookings(Integer id) {
        return null;
    }

    @Override
    public List<Integer> findAllUsers(int id) {
        return null;
    }

    @Override
    public Flight findByDateAndAirport(Date date, String airport) {
        Optional<Flight> flight = flightRepository.findById(flightRepository.findByDateAndAirport(date, airport));
        return flight.orElse(null);
    }
}

