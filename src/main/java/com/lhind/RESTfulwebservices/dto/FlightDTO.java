package com.lhind.RESTfulwebservices.dto;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class FlightDTO {
    private String airline;
    private String origin;
    private String destination;
    private String status;
    private Date departureDate;
    private Date arrivalDate;
    private Integer flightNumber;
    private List<Integer> bookingIds;

    public FlightDTO() {
    }

    public FlightDTO(String airline, String origin, String destination, String status, Date departureDate, Date arrivalDate) {
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public List<Integer> getBookingIds() {
        return bookingIds;
    }

    public void setBookingIds(List<Integer> bookingIds) {
        this.bookingIds = bookingIds;
    }

    @Override
    public String toString() {
        return "FlightDTO{" +
                "airline='" + airline + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", status='" + status + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                '}';
    }
}
