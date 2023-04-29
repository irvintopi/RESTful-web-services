package com.lhind.RESTfulwebservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Data
public class FlightDTO {
    private String airline;
    private String origin;
    private String destination;
    private String status;
    private Date departureDate;
    private Date arrivalDate;
    private Integer flightNumber;
}
