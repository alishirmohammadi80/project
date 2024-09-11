package com.alishirmohammadi.AirlineTicketBookingSystem.service;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Ticket;

import java.util.List;

public interface TicketService {
   // Ticket bookTicket(FlightSchedule flightSchedule, Customer customer);
    public List<Ticket> findAllTicket();
    public List<Ticket> searchTicket(String keyword);
    public Ticket findTicketById(Long id);
    public void saveTicket(Ticket ticket);
    public void updateTicket(Ticket ticket);
    public void deleteTicket(Long id);
}
