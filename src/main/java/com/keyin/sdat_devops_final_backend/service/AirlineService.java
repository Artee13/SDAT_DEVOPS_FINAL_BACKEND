package com.keyin.sdat_devops_final_backend.service;

import com.keyin.sdat_devops_final_backend.entity.Airline;
import com.keyin.sdat_devops_final_backend.repository.AirlineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineService {

    private final AirlineRepository airlineRepository;

    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    public List<Airline> getAll() {
        return airlineRepository.findAll();
    }

    public Airline getById(Long id) {
        return airlineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airline not found: " + id));
    }

    public Airline create(Airline airline) {
        return airlineRepository.save(airline);
    }

    public Airline update(Long id, Airline updated) {
        Airline existing = getById(id);

        existing.setCode(updated.getCode());
        existing.setName(updated.getName());
        existing.setCountry(updated.getCountry());
        existing.setLogoUrl(updated.getLogoUrl());

        return airlineRepository.save(existing);
    }

    public void delete(Long id) {
        airlineRepository.delete(getById(id));
    }
}
