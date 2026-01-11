package com.keyin.sdat_devops_final_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String flightNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightStatus status;

    @Column(nullable = false)
    private LocalDateTime scheduledTime;

    private LocalDateTime estimatedTime;

    private String origin;
    private String destination;

    @ManyToOne(optional = false)
    @JoinColumn(name = "airport_id")
    private Airport airport;

    @ManyToOne(optional = false)
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @ManyToOne(optional = false)
    @JoinColumn(name = "gate_id")
    private Gate gate;

    public Flight() {}

    public Flight(String flightNumber, FlightType type, FlightStatus status,
                  LocalDateTime scheduledTime, LocalDateTime estimatedTime,
                  String origin, String destination,
                  Airport airport, Airline airline, Gate gate) {
        this.flightNumber = flightNumber;
        this.type = type;
        this.status = status;
        this.scheduledTime = scheduledTime;
        this.estimatedTime = estimatedTime;
        this.origin = origin;
        this.destination = destination;
        this.airport = airport;
        this.airline = airline;
        this.gate = gate;
    }

    public Long getId() { return id; }
    public String getFlightNumber() { return flightNumber; }
    public FlightType getType() { return type; }
    public FlightStatus getStatus() { return status; }
    public LocalDateTime getScheduledTime() { return scheduledTime; }
    public LocalDateTime getEstimatedTime() { return estimatedTime; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public Airport getAirport() { return airport; }
    public Airline getAirline() { return airline; }
    public Gate getGate() { return gate; }

    public void setId(Long id) { this.id = id; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public void setType(FlightType type) { this.type = type; }
    public void setStatus(FlightStatus status) { this.status = status; }
    public void setScheduledTime(LocalDateTime scheduledTime) { this.scheduledTime = scheduledTime; }
    public void setEstimatedTime(LocalDateTime estimatedTime) { this.estimatedTime = estimatedTime; }
    public void setOrigin(String origin) { this.origin = origin; }
    public void setDestination(String destination) { this.destination = destination; }
    public void setAirport(Airport airport) { this.airport = airport; }
    public void setAirline(Airline airline) { this.airline = airline; }
    public void setGate(Gate gate) { this.gate = gate; }
}
