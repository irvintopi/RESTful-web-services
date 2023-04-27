package com.lhind.RESTfulwebservices.Controller;

import com.lhind.RESTfulwebservices.dto.FlightDTO;
import com.lhind.RESTfulwebservices.mapper.FlightMapper;
import com.lhind.RESTfulwebservices.model.Flight;
import com.lhind.RESTfulwebservices.services.BookingService;
import com.lhind.RESTfulwebservices.services.FlightService;
import com.lhind.RESTfulwebservices.services.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
         FlightDTO flightDTO = flightMapper.toDto(flightService.findById(id).get());
        return flightDTO;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{date}/{airport}")
    public FlightDTO findFlightByDateAndAirport(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @PathVariable("airport") String airport){
        return flightMapper.toDto(flightService.findByDateAndAirport(date, airport));
    }
}
