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

    @GetMapping("/{id}")
    public Airport getById(@PathVariable Long id) {
        return airportService.getById(id);
    }

    @PostMapping
    public Airport create(@RequestBody Airport airport) {
        return airportService.create(airport);
    }

    @PutMapping("/{id}")
    public Airport update(@PathVariable Long id, @RequestBody Airport airport) {
        return airportService.update(id, airport);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        airportService.delete(id);
    }
}
