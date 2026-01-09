package com.keyin.sdat_devops_final_backend.repository;

import com.keyin.sdat_devops_final_backend.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByCode(String code);
}
