package com.lhind.RESTfulwebservices.Controller;

import com.lhind.RESTfulwebservices.dto.BookingDTO;
import com.lhind.RESTfulwebservices.dto.FlightDTO;
import com.lhind.RESTfulwebservices.mapper.BookingMapper;
import com.lhind.RESTfulwebservices.model.Booking;
import com.lhind.RESTfulwebservices.services.BookingService;
import com.lhind.RESTfulwebservices.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {
    BookingService bookingService;
    UserService userService;
    BookingMapper bookingMapper;

    //get all bookings sorted by date
    @RequestMapping(method = RequestMethod.GET)
    public List<BookingDTO> get() {
        List<BookingDTO> bookings = bookingService.findAll();
        Collections.sort(bookings, Comparator.comparing(BookingDTO::getBookingDate));
        return bookings;
    }

    //get specific bookings of a user
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/{userId}")
    public BookingDTO getBookingOfAUser(@PathVariable Integer id, @PathVariable Integer userId){
       BookingDTO booking = userService.findBookingByIdAndUser(id , userId);
       return booking;
    }

    //get all bookings of a user
    @RequestMapping(method = RequestMethod.GET, value = {"/{id}"})
    public List<BookingDTO> getAllBookingsOfUser(@PathVariable Integer id) {
        List<BookingDTO> bookingDTOS = userService.findAllBookings(id);
        return bookingDTOS;
    }

    //get flights of a booking
    @RequestMapping(method = RequestMethod.GET ,value = "/{id}/flights")
    public List<FlightDTO> getFlightsOfABooking(@PathVariable(name = "id") Integer id){
        Optional<Booking> bookingOptional = bookingService.findById(id);

        bookingOptional.ifPresent(booking -> bookingService.findById(id));

        List<FlightDTO> flightDtoList = bookingService.findAllFlights(id);
        return flightDtoList;
    }

    //add a booking
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public BookingDTO addBooking(@RequestBody Booking booking) {
        bookingService.save(booking);
        return bookingMapper.toDto(booking);
    }

    //delete booking
    @RequestMapping(method =RequestMethod.DELETE, value = "/{id}")
    public void deleteBooking(@PathVariable Integer id) {
        Optional<Booking> bookingOptional = bookingService.findById(id);

        bookingOptional.ifPresent(booking -> bookingService.delete(booking));
    }
}
