package com.lhind.RESTfulwebservices.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "flight")
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer id;

    @Column
    private String origin;

    @Column
    private String destination;

    @Column
    private String airline;

    @Column(name = "flight_number")
    private Integer flightNumber;

    @Column(name = "departure_date")
    private Date departureDate;

    @Column(name = "arrival_date")
    private Date arrivalDate;

    @Column
    private String status;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "flights")
    private List<Booking> bookings;
}