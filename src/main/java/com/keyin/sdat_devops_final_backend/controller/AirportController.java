package com.keyin.sdat_devops_final_backend.controller;

import com.keyin.sdat_devops_final_backend.entity.Airport;
import com.keyin.sdat_devops_final_backend.service.AirportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@CrossOrigin(origins = "*")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<Airport> getAll() {
        return airportService.getAll();
    }

    @PostMapping
    public Airport create(@RequestBody Airport airport) {
        return airportService.create(airport);
    }
}

