package com.keyin.sdat_devops_final_backend.service;

import com.keyin.sdat_devops_final_backend.controller.dto.FlightRequest;
import com.keyin.sdat_devops_final_backend.entity.*;
import com.keyin.sdat_devops_final_backend.exception.NotFoundException;
import com.keyin.sdat_devops_final_backend.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final AirlineRepository airlineRepository;
    private final GateRepository gateRepository;

    public FlightService(FlightRepository flightRepository,
                         AirportRepository airportRepository,
                         AirlineRepository airlineRepository,
                         GateRepository gateRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.airlineRepository = airlineRepository;
        this.gateRepository = gateRepository;
    }

    public List<Flight> getAll(Long airportId, FlightType type) {
        if (airportId != null && type != null) {
            return flightRepository.findByAirportIdAndType(airportId, type);
        }
        return flightRepository.findAll();
    }

    public Flight getById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found: " + id));
    }

    public Flight create(FlightRequest req) {
        return flightRepository.save(buildFlightFromRequest(new Flight(), req));
    }

    public Flight update(Long id, FlightRequest req) {
        Flight existing = getById(id);
        return flightRepository.save(buildFlightFromRequest(existing, req));
    }

    public void delete(Long id) {
        flightRepository.delete(getById(id));
    }

    private Flight buildFlightFromRequest(Flight flight, FlightRequest req) {
        Airport airport = airportRepository.findById(req.airportId)
                .orElseThrow(() -> new NotFoundException("Airport not found: " + req.airportId));
        Airline airline = airlineRepository.findById(req.airlineId)
                .orElseThrow(() -> new NotFoundException("Airline not found: " + req.airlineId));
        Gate gate = gateRepository.findById(req.gateId)
                .orElseThrow(() -> new NotFoundException("Gate not found: " + req.gateId));

        flight.setFlightNumber(req.flightNumber);
        flight.setType(FlightType.valueOf(req.type));
        flight.setStatus(FlightStatus.valueOf(req.status));
        flight.setScheduledTime(LocalDateTime.parse(req.scheduledTime));
        flight.setEstimatedTime(req.estimatedTime == null || req.estimatedTime.isBlank()
                ? null : LocalDateTime.parse(req.estimatedTime));

        flight.setOrigin(req.origin);
        flight.setDestination(req.destination);

        flight.setAirport(airport);
        flight.setAirline(airline);
        flight.setGate(gate);

        return flight;
    }
}
