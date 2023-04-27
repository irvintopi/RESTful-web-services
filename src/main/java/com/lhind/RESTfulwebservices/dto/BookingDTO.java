package com.lhind.RESTfulwebservices.dto;


import com.lhind.RESTfulwebservices.model.Flight;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.List;
@Component
public class BookingDTO {

    private String userName;
    private String status;
    private Date bookingDate;
    private List<FlightDTO> flights;
    private List<Integer> flightIds;

    public BookingDTO() {
    }

    public BookingDTO(String userName, String status, Date bookingDate) {
        this.userName = userName;
        this.status = status;
        this.bookingDate = bookingDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public List<FlightDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightDTO> flights) {
        this.flights = flights;
    }

    public List<Integer> getFlightIds() {
        return flightIds;
    }

    public void setFlightIds(List<Integer> flightIds) {
        this.flightIds = flightIds;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "userName='" + userName + '\'' +
                ", status='" + status + '\'' +
                ", bookingDate=" + bookingDate +
                '}';
    }
}