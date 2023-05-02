package com.lhind.RESTfulwebservices.repository;

import com.lhind.RESTfulwebservices.dto.BookingDTO;
import com.lhind.RESTfulwebservices.model.Booking;
import com.lhind.RESTfulwebservices.model.User;
import com.lhind.RESTfulwebservices.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT id FROM booking WHERE user_id = :id", nativeQuery = true)
    List<Integer> findAllBookingsOfAUser(@Param("id") Integer id);

    @Query(value = "SELECT id FROM booking WHERE user_id = :userId AND id = :bookingId", nativeQuery = true)
    Integer findBookingByIdAndUser(@Param("bookingId") Integer bookingId, @Param("userId") Integer userId);
}