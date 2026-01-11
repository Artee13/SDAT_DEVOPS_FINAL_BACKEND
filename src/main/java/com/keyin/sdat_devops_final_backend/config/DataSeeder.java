package com.keyin.sdat_devops_final_backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.keyin.sdat_devops_final_backend.repository.AirportRepository;
import com.keyin.sdat_devops_final_backend.repository.AirlineRepository;
import com.keyin.sdat_devops_final_backend.repository.GateRepository;

import com.keyin.sdat_devops_final_backend.entity.*;
import com.keyin.sdat_devops_final_backend.repository.FlightRepository;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedAirports(AirportRepository airportRepository) {
        return args -> {
            if (airportRepository.count() == 0) {

                airportRepository.save(new Airport(
                        "YYT",
                        "St. John's International Airport",
                        "St. John's",
                        "Canada",
                        "America/St_Johns"
                ));

                airportRepository.save(new Airport(
                        "YYZ",
                        "Toronto Pearson International Airport",
                        "Toronto",
                        "Canada",
                        "America/Toronto"
                ));

                airportRepository.save(new Airport(
                        "JFK",
                        "John F. Kennedy International Airport",
                        "New York",
                        "USA",
                        "America/New_York"
                ));
            }
        };
    }

    @Bean
CommandLineRunner seedAirlines(AirlineRepository airlineRepository) {
    return args -> {
        if (airlineRepository.count() == 0) {

            airlineRepository.save(new Airline(
                    "AC",
                    "Air Canada",
                    "Canada",
                    null
            ));

            airlineRepository.save(new Airline(
                    "WS",
                    "WestJet",
                    "Canada",
                    null
            ));

            airlineRepository.save(new Airline(
                    "DL",
                    "Delta Air Lines",
                    "USA",
                    null
            ));
        }
    };
}

@Bean
CommandLineRunner seedGates(GateRepository gateRepository, AirportRepository airportRepository) {
    return args -> {
        if (gateRepository.count() == 0) {

            Airport yyt = airportRepository.findByCode("YYT")
                    .orElseThrow(() -> new RuntimeException("YYT not found"));
            Airport yyz = airportRepository.findByCode("YYZ")
                    .orElseThrow(() -> new RuntimeException("YYZ not found"));

            gateRepository.save(new Gate("A1", "T1", yyt));
            gateRepository.save(new Gate("A2", "T1", yyt));

            gateRepository.save(new Gate("B3", "T1", yyz));
            gateRepository.save(new Gate("B4", "T1", yyz));
        }
    };
}


@Bean
CommandLineRunner seedFlights(FlightRepository flightRepository,
                              AirportRepository airportRepository,
                              AirlineRepository airlineRepository,
                              GateRepository gateRepository) {
    return args -> {
        if (flightRepository.count() == 0) {

            Airport yyt = airportRepository.findByCode("YYT").orElseThrow();
            Airport yyz = airportRepository.findByCode("YYZ").orElseThrow();

            Airline ac = airlineRepository.findByCode("AC").orElseThrow();
            Airline ws = airlineRepository.findByCode("WS").orElseThrow();

            // Pick gates by id  - A1 is usually id=1 etc.
            Gate a1 = gateRepository.findById(1L).orElseThrow();
            Gate b3 = gateRepository.findById(3L).orElseThrow();

            flightRepository.save(new Flight(
                    "AC101",
                    FlightType.DEPARTURE,
                    FlightStatus.BOARDING,
                    java.time.LocalDateTime.now().plusHours(1),
                    null,
                    "St. John's",
                    "Toronto",
                    yyt, ac, a1
            ));

            flightRepository.save(new Flight(
                    "WS220",
                    FlightType.ARRIVAL,
                    FlightStatus.ON_TIME,
                    java.time.LocalDateTime.now().plusMinutes(45),
                    null,
                    "Toronto",
                    "St. John's",
                    yyt, ws, a1
            ));

            flightRepository.save(new Flight(
                    "AC555",
                    FlightType.DEPARTURE,
                    FlightStatus.ON_TIME,
                    java.time.LocalDateTime.now().plusHours(2),
                    null,
                    "Toronto",
                    "St. John's",
                    yyz, ac, b3
            ));
        }
    };
}


}

