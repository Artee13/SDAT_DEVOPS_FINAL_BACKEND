package com.keyin.sdat_devops_final_backend.repository;

import com.keyin.sdat_devops_final_backend.entity.Flight;
import com.keyin.sdat_devops_final_backend.entity.FlightType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByAirportIdAndType(Long airportId, FlightType type);
}
