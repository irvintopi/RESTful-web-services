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
    @Query(value = "SELECT id from Flight where departure_date = :date and origin = :airport", nativeQuery = true)
    Integer findByDateAndAirport(@Param("date")Date date, @Param("airport") String airport);
}
