package com.keyin.sdat_devops_final_backend.service;

import com.keyin.sdat_devops_final_backend.controller.dto.FlightRequest;
import com.keyin.sdat_devops_final_backend.controller.dto.FlightResponse;
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

    public List<FlightResponse> getAll(Long airportId, FlightType type) {
        List<Flight> flights;

        if (airportId != null && type != null) {
            flights = flightRepository.findByAirportIdAndType(airportId, type);
        } else {
            flights = flightRepository.findAll();
        }

        return flights.stream().map(this::toResponse).toList();
    }

    public FlightResponse getById(Long id) {
        Flight flight = getEntityById(id);
        return toResponse(flight);
    }

    public Flight create(FlightRequest req) {
        return flightRepository.save(buildFlightFromRequest(new Flight(), req));
    }

    public Flight update(Long id, FlightRequest req) {
        Flight existing = getEntityById(id);
        return flightRepository.save(buildFlightFromRequest(existing, req));
    }

    public void delete(Long id) {
        flightRepository.delete(getEntityById(id));
    }

    private Flight getEntityById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Flight not found: " + id));
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

    private FlightResponse toResponse(Flight flight) {
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

        return r;
    }
}
