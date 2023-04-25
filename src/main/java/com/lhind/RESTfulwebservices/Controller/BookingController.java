package com.lhind.RESTfulwebservices.Controller;

import com.lhind.RESTfulwebservices.dto.BookingDTO;
import com.lhind.RESTfulwebservices.dto.FlightDTO;
import com.lhind.RESTfulwebservices.model.Booking;
import com.lhind.RESTfulwebservices.services.BookingService;
import com.lhind.RESTfulwebservices.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.xpath.XPathVariableResolver;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
public class BookingController {
    BookingService bookingService;
    UserService userService;

    BookingController(BookingService bookingService, UserService userService){
        this.bookingService=bookingService;
        this.userService=userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/all"})
    public List<BookingDTO> get() {
        List<BookingDTO> bookings = bookingService.findAll();
        Collections.sort(bookings, Comparator.comparing(BookingDTO::getBookingDate));
        return bookings;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/all/{id}"})
    public List<BookingDTO> getBookingsById(@PathVariable Integer id) {
        List<BookingDTO> bookingDTOS = userService.findAllBookings(id);
        return bookingDTOS;
    }
    @RequestMapping(method = RequestMethod.GET ,value = "/{id}/flights")
    public List<Integer> getFlightsOfABooking(@PathVariable(name = "id") Integer id){
        Optional<Booking> bookingOptional = bookingService.findById(id);

        bookingOptional.ifPresent(booking -> bookingService.findById(id));

        List<Integer> flightDtoList = bookingService.findAllFlights(id);
        return flightDtoList;
    }
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public void add() {
        Booking booking = new Booking();
        booking = bookingService.save(booking);
    }


    @RequestMapping(method =RequestMethod.DELETE, value = "/delete/{id}")
    public void delete(@PathVariable Integer id) {
        Optional<Booking> bookingOptional = bookingService.findById(id);

        bookingOptional.ifPresent(booking -> bookingService.delete(booking));
    }
}
