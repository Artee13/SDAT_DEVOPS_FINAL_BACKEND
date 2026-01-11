package com.keyin.sdat_devops_final_backend.controller.dto;

import com.keyin.sdat_devops_final_backend.entity.FlightStatus;
import com.keyin.sdat_devops_final_backend.entity.FlightType;

import java.time.LocalDateTime;

public class FlightResponse {
    public Long id;

    public String flightNumber;
    public FlightType type;
    public FlightStatus status;

    public LocalDateTime scheduledTime;
    public LocalDateTime estimatedTime;

    public String origin;
    public String destination;

    public Long airportId;
    public String airportCode;

    public Long airlineId;
    public String airlineCode;
    public String airlineName;

    public Long gateId;
    public String gateName;
    public String gateTerminal;
}
