package com.lhind.RESTfulwebservices.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "user_details")
@Data
public class UserDetails {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(nullable = false, unique = true, name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private  String lastName;
    @Column
    private String email;
    @Column(name="phone_number")
    private String phoneNumber;
}