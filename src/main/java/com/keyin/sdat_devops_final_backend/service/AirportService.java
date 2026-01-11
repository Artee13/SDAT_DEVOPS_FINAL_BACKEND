package com.keyin.sdat_devops_final_backend.service;

import com.keyin.sdat_devops_final_backend.entity.Airport;
import com.keyin.sdat_devops_final_backend.exception.NotFoundException;
import com.keyin.sdat_devops_final_backend.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAll() {
        return airportRepository.findAll();
    }

    public Airport getById(Long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Airport not found: " + id));
    }

    public Airport create(Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport update(Long id, Airport updated) {
        Airport existing = getById(id);

        existing.setCode(updated.getCode());
        existing.setName(updated.getName());
        existing.setCity(updated.getCity());
        existing.setCountry(updated.getCountry());
        existing.setTimezone(updated.getTimezone());

        return airportRepository.save(existing);
    }

    public void delete(Long id) {
        Airport existing = getById(id);
        airportRepository.delete(existing);
    }
}
