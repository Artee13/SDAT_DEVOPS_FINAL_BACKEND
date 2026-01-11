package com.keyin.sdat_devops_final_backend.service;

import com.keyin.sdat_devops_final_backend.entity.Airport;
import com.keyin.sdat_devops_final_backend.entity.Gate;
import com.keyin.sdat_devops_final_backend.repository.AirportRepository;
import com.keyin.sdat_devops_final_backend.repository.GateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateService {

    private final GateRepository gateRepository;
    private final AirportRepository airportRepository;

    public GateService(GateRepository gateRepository, AirportRepository airportRepository) {
        this.gateRepository = gateRepository;
        this.airportRepository = airportRepository;
    }

    public List<Gate> getAll() {
        return gateRepository.findAll();
    }

    public Gate getById(Long id) {
        return gateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gate not found: " + id));
    }

    // We accept airportId so frontend/admin can create a gate easily
    public Gate create(String name, String terminal, Long airportId) {
        Airport airport = airportRepository.findById(airportId)
                .orElseThrow(() -> new RuntimeException("Airport not found: " + airportId));

        Gate gate = new Gate(name, terminal, airport);
        return gateRepository.save(gate);
    }

    public Gate update(Long id, String name, String terminal, Long airportId) {
        Gate existing = getById(id);

        Airport airport = airportRepository.findById(airportId)
                .orElseThrow(() -> new RuntimeException("Airport not found: " + airportId));

        existing.setName(name);
        existing.setTerminal(terminal);
        existing.setAirport(airport);

        return gateRepository.save(existing);
    }

    public void delete(Long id) {
        gateRepository.delete(getById(id));
    }
}
