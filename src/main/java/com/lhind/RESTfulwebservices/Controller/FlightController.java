package com.lhind.RESTfulwebservices.Controller;

import com.lhind.RESTfulwebservices.dto.FlightDTO;
import com.lhind.RESTfulwebservices.mapper.FlightMapper;
import com.lhind.RESTfulwebservices.model.Flight;
import com.lhind.RESTfulwebservices.services.BookingService;
import com.lhind.RESTfulwebservices.services.FlightService;
import com.lhind.RESTfulwebservices.services.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    BookingService bookingService;
    UserService userService;
    FlightService flightService;
    FlightMapper flightMapper;

    FlightController(BookingService bookingService, UserService userService, FlightMapper flightMapper,FlightService flightService){
        this.bookingService = bookingService;
        this.userService = userService;
        this.flightMapper = flightMapper;
        this.flightService= flightService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<FlightDTO> findAll(){
     return flightService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public FlightDTO findById(@PathVariable("id") Integer id){
        return flightMapper.toDto(flightService.findById(id).get());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{date}/{airport}")
    public FlightDTO findFlightByDateAndAirport(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @PathVariable("airport") String airport){
        return flightMapper.toDto(flightService.findByDateAndAirport(date, airport));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<FlightDTO> saveNewFlight(@RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.save(flight));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void delete(@PathVariable("id") Integer id){
        flightService.delete(flightService.findById(id).get());
    }
}
