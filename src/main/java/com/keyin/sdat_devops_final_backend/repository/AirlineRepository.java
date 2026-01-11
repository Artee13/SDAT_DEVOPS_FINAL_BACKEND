package com.keyin.sdat_devops_final_backend.repository;

import com.keyin.sdat_devops_final_backend.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    Optional<Airline> findByCode(String code);
}
