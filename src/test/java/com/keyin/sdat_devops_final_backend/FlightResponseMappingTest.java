package com.keyin.sdat_devops_final_backend;

import com.keyin.sdat_devops_final_backend.controller.dto.FlightResponse;
import com.keyin.sdat_devops_final_backend.entity.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightResponseMappingTest {

    @Test
    void flightResponseFieldsMatchEntity() {
        Airport airport = new Airport("YYT", "St. John's International Airport", "St. John's", "Canada", "America/St_Johns");
        airport.setId(1L);

        Airline airline = new Airline("AC", "Air Canada", "Canada", null);
        airline.setId(2L);

        Gate gate = new Gate("A1", "T1", airport);
        gate.setId(3L);

        Flight flight = new Flight();
        flight.setId(10L);
        flight.setFlightNumber("AC101");
        flight.setType(FlightType.DEPARTURE);
        flight.setStatus(FlightStatus.BOARDING);
        flight.setScheduledTime(LocalDateTime.parse("2026-01-11T16:40:49"));
        flight.setEstimatedTime(null);
        flight.setOrigin("St. John's");
        flight.setDestination("Toronto");
        flight.setAirport(airport);
        flight.setAirline(airline);
        flight.setGate(gate);

        FlightResponse r = new FlightResponse();
        r.id = flight.getId();
        r.flightNumber = flight.getFlightNumber();
        r.type = flight.getType();
        r.status = flight.getStatus();
        r.scheduledTime = flight.getScheduledTime();
        r.estimatedTime = flight.getEstimatedTime();
        r.origin = flight.getOrigin();
        r.destination = flight.getDestination();
        r.airportId = flight.getAirport().getId();
        r.airportCode = flight.getAirport().getCode();
        r.airlineId = flight.getAirline().getId();
        r.airlineCode = flight.getAirline().getCode();
        r.airlineName = flight.getAirline().getName();
        r.gateId = flight.getGate().getId();
        r.gateName = flight.getGate().getName();
        r.gateTerminal = flight.getGate().getTerminal();

        assertEquals(10L, r.id);
        assertEquals("AC101", r.flightNumber);
        assertEquals(FlightType.DEPARTURE, r.type);
        assertEquals(FlightStatus.BOARDING, r.status);
        assertEquals("YYT", r.airportCode);
        assertEquals("AC", r.airlineCode);
        assertEquals("A1", r.gateName);
    }
}
