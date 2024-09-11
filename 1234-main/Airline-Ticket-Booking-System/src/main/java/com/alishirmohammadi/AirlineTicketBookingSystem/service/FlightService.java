package com.alishirmohammadi.AirlineTicketBookingSystem.service;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Flight;
import java.util.List;
public interface FlightService {
    public List<Flight> findAllFlight();
    public List<Flight> searchFlight(String keyword);
    public List<Flight> searchAvailableTickets(String keyword);
    public Flight findFlightById(Long id);
    public void saveFlight(Flight flight);
    public void updateFlight(Flight flight);
    public void deleteFlight(Long id);
}
