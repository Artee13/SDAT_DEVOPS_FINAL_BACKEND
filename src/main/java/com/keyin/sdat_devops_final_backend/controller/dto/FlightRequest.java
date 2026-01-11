package com.keyin.sdat_devops_final_backend.controller.dto;

public class FlightRequest {
    public String flightNumber;
    public String type;        // "ARRIVAL" / "DEPARTURE"
    public String status;      // "ON_TIME", etc.
    public String scheduledTime; // ISO string: "2026-01-11T12:30:00"
    public String estimatedTime; // ISO or null
    public String origin;
    public String destination;

    public Long airportId;
    public Long airlineId;
    public Long gateId;
}

