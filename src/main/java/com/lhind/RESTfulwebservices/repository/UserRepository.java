package com.lhind.RESTfulwebservices.repository;

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

    public User findUserByBookings(Booking b);
    public User findAllByUserDetails(UserDetails ud);
    @Query(value = "SELECT * FROM booking WHERE user_id= :id", nativeQuery = true)
    List<Booking> findAllByUserId(Integer id);
    /*@Query(value = "SELECT * FROM booking WHERE user_id= :id", nativeQuery = true)*/
    /*public List<Booking> findAllById(@Param("id") Integer id);*/
    /*@Query(value = "SELECT flight_id FROM booking_flight WHERE booking_id = :bookingId", nativeQuery = true)*/
    /*public List<Integer> findAllById(@Param("bookingId") int id);*/
    @Query(value = "SELECT id FROM booking WHERE user_id = :id", nativeQuery = true)
    public List<Integer> findAllBookingsOfAUser(@Param("id") Integer id);

}