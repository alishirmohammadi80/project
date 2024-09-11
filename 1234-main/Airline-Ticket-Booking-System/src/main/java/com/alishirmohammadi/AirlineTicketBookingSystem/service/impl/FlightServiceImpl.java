package com.alishirmohammadi.AirlineTicketBookingSystem.service.impl;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Flight;
import com.alishirmohammadi.AirlineTicketBookingSystem.repository.FlightRepository;
import com.alishirmohammadi.AirlineTicketBookingSystem.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    FlightRepository flightRepository;
    @Override
    public List<Flight> findAllFlight() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> searchFlight(String keyword) {
        if (keyword != null) {
            return flightRepository.searchFlight(keyword);
        }
        return flightRepository.findAll();

    }

    @Override
    public List<Flight> searchAvailableTickets(String keyword) {
        if (keyword != null) {
            return flightRepository.searchFlight(keyword);
        }
        return flightRepository.findAll();
    }

    @Override
    public Flight findFlightById(Long id) {
        return flightRepository.getById(id);
    }

    @Override
    public void saveFlight(Flight flight) {
flightRepository.save(flight);
    }

    @Override
    public void updateFlight(Flight flight) {
flightRepository.save(flight);
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}
