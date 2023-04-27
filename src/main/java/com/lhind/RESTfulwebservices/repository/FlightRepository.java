package com.lhind.RESTfulwebservices.repository;
import com.lhind.RESTfulwebservices.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Query(value = "SELECT booking_id FROM booking_flight WHERE flight_id = :id", nativeQuery = true)
    List<Integer> findAllById(@Param("id") Integer id);
    @Query(value = "select user_id from booking where id IN(select booking_id from booking_flight where flight_id = :flightId)", nativeQuery = true)
    List<Integer> findAllById(@Param("flightId") int id);

    @Query(value = "SELECT id from Flight where departure_date = :date and origin = :airport", nativeQuery = true)
    Integer findByDateAndAirport(@Param("date")Date date, @Param("airport") String airport);


}
