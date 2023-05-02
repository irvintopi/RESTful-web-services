package com.lhind.RESTfulwebservices.repository;

import com.lhind.RESTfulwebservices.model.Booking;
import com.lhind.RESTfulwebservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query(value = "SELECT flight_id FROM jpa_exercise_db.booking_flight WHERE booking_id = :id", nativeQuery = true)
    List<Integer> findAllById(@Param("id") Integer id);

    @Query("SELECT b FROM Booking b JOIN b.flights f WHERE f.id = :flightId")
    List<Booking> findByFlightId(@Param("flightId") Integer flightId);
}