package com.lhind.RESTfulwebservices.Controller;

import com.lhind.RESTfulwebservices.dto.FlightDTO;
import com.lhind.RESTfulwebservices.mapper.FlightMapper;
import com.lhind.RESTfulwebservices.model.Flight;
import com.lhind.RESTfulwebservices.services.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
@AllArgsConstructor
public class FlightController {
    FlightService flightService;
    FlightMapper flightMapper;

    //find all flights
    @RequestMapping(method = RequestMethod.GET)
    public List<FlightDTO> findAll(){
     return flightService.findAll();
    }

    //find flight by ud
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<FlightDTO> findById(@PathVariable("id") Integer id) {
        Optional<Flight> flightOptional = flightService.findById(id);
        return flightOptional.map(flight -> ResponseEntity.ok(flightMapper.toDto(flight))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //find flight by date and airport
    @RequestMapping(method = RequestMethod.GET, value = "/{date}/{airport}")
    public FlightDTO findFlightByDateAndAirport(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @PathVariable("airport") String airport){
        return flightMapper.toDto(flightService.findByDateAndAirport(date, airport));
    }

    //add new flight
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<FlightDTO> saveNewFlight(@RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.save(flight));
    }

    //delete flight
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void delete(@PathVariable("id") Integer id){
        flightService.delete(flightService.findById(id).get());
    }
}
