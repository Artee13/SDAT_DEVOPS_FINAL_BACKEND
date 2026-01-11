package com.keyin.sdat_devops_final_backend.controller;

import com.keyin.sdat_devops_final_backend.controller.dto.FlightRequest;
import com.keyin.sdat_devops_final_backend.entity.Flight;
import com.keyin.sdat_devops_final_backend.entity.FlightType;
import com.keyin.sdat_devops_final_backend.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "*")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> getAll(
            @RequestParam(required = false) Long airportId,
            @RequestParam(required = false) String type
    ) {
        FlightType parsedType = (type == null) ? null : FlightType.valueOf(type);
        return flightService.getAll(airportId, parsedType);
    }

    @GetMapping("/{id}")
    public Flight getById(@PathVariable Long id) {
        return flightService.getById(id);
    }

    @PostMapping
    public Flight create(@RequestBody FlightRequest req) {
        return flightService.create(req);
    }

    @PutMapping("/{id}")
    public Flight update(@PathVariable Long id, @RequestBody FlightRequest req) {
        return flightService.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        flightService.delete(id);
    }
}
