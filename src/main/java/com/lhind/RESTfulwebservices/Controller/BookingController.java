package com.lhind.RESTfulwebservices.Controller;

import com.lhind.RESTfulwebservices.dto.BookingDTO;
import com.lhind.RESTfulwebservices.dto.FlightDTO;
import com.lhind.RESTfulwebservices.mapper.BookingMapper;
import com.lhind.RESTfulwebservices.model.Booking;
import com.lhind.RESTfulwebservices.services.BookingService;
import com.lhind.RESTfulwebservices.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    BookingService bookingService;
    UserService userService;

    BookingMapper bookingMapper;

    BookingController(BookingService bookingService, UserService userService, BookingMapper bookingMapper){
        this.bookingService = bookingService;
        this.userService = userService;
        this.bookingMapper = bookingMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<BookingDTO> get() {
        List<BookingDTO> bookings = bookingService.findAll();
        Collections.sort(bookings, Comparator.comparing(BookingDTO::getBookingDate));
        return bookings;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/{userId}")
    public BookingDTO getBookingOfAUser(@PathVariable Integer id, @PathVariable Integer userId){
       BookingDTO booking = userService.findBookingByIdAndUser(id , userId);
       return booking;
    }
    @RequestMapping(method = RequestMethod.GET, value = {"/{id}"})
    public List<BookingDTO> getAllBookingsOfUser(@PathVariable Integer id) {
        List<BookingDTO> bookingDTOS = userService.findAllBookings(id);
        return bookingDTOS;
    }

    @RequestMapping(method = RequestMethod.GET ,value = "/{id}/flights")
    public List<FlightDTO> getFlightsOfABooking(@PathVariable(name = "id") Integer id){
        Optional<Booking> bookingOptional = bookingService.findById(id);

        bookingOptional.ifPresent(booking -> bookingService.findById(id));

        List<FlightDTO> flightDtoList = bookingService.findAllFlights(id);
        return flightDtoList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public BookingDTO add(@RequestBody Booking booking) {
        bookingService.save(booking);
        return bookingMapper.toDto(booking);
    }


    @RequestMapping(method =RequestMethod.DELETE, value = "/delete/{id}")
    public void delete(@PathVariable Integer id) {
        Optional<Booking> bookingOptional = bookingService.findById(id);

        bookingOptional.ifPresent(booking -> bookingService.delete(booking));
    }
}
