package com.keyin.sdat_devops_final_backend.controller;

import com.keyin.sdat_devops_final_backend.entity.Airline;
import com.keyin.sdat_devops_final_backend.service.AirlineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
@CrossOrigin(origins = "*")
public class AirlineController {

    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping
    public List<Airline> getAll() {
        return airlineService.getAll();
    }

    @GetMapping("/{id}")
    public Airline getById(@PathVariable Long id) {
        return airlineService.getById(id);
    }

    @PostMapping
    public Airline create(@RequestBody Airline airline) {
        return airlineService.create(airline);
    }

    @PutMapping("/{id}")
    public Airline update(@PathVariable Long id, @RequestBody Airline airline) {
        return airlineService.update(id, airline);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        airlineService.delete(id);
    }
}

