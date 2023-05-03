package com.lhind.RESTfulwebservices.repository;

import com.lhind.RESTfulwebservices.model.Booking;
import com.lhind.RESTfulwebservices.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findAllByUserId(Integer id);

    Booking findByIdAndUserId(Integer id, Integer userId);

    List<Booking> findByFlights(Flight flight);

}