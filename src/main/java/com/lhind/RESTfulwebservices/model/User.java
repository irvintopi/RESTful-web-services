package com.lhind.RESTfulwebservices.model;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Integer id;

    @Column(name = "user_name",unique = true)
    private String userName;

    @Column
    private String password;

    @Column
    private String role;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private UserDetails userDetails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Booking> bookings;
}