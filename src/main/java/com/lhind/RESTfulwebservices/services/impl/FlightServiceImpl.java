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
    private FlightDTO flightDTO;
    private FlightMapper flightMapper;

    FlightServiceImpl(FlightRepository flightRepository, FlightDTO flightDTO, FlightMapper flightMapper){
        this.flightRepository = flightRepository;
        this.flightDTO = flightDTO;
        this.flightMapper = flightMapper;
    }

    public Flight save(Flight f) { return flightRepository.save(f); }

    public Optional<Flight> findById(Integer id) {
        return flightRepository.findById(id);
    }

    public List<FlightDTO> findAll() {
        List<Flight> flights = flightRepository.findAll();
        List<FlightDTO> flightDTOs = new ArrayList<>();
        for (Flight flight : flights) {
            flightDTOs.add(flightMapper.toDto(flight));
        }
        return flightDTOs;
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

    @Override
    public Flight findByDateAndAirport(Date date, String airport) {
        Optional<Flight> flight = flightRepository.findById(flightRepository.findByDateAndAirport(date, airport));
        return flight.orElse(null);
    }
}

