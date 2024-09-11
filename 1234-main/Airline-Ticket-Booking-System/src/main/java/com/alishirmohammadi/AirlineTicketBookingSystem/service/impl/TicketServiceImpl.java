package com.alishirmohammadi.AirlineTicketBookingSystem.service.impl;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Ticket;
import com.alishirmohammadi.AirlineTicketBookingSystem.repository.TicketRepository;
import com.alishirmohammadi.AirlineTicketBookingSystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TicketServiceImpl extends Ticket implements TicketService {
@Autowired
    TicketRepository ticketRepository;

    @Override
    public List<Ticket> findAllTicket() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> searchTicket(String keyword) {
        if (keyword != null) {
            return ticketRepository.searchTicket(keyword);
        }
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findTicketById(Long id) {
        return ticketRepository.getById(id);
    }

    @Override
    public void saveTicket(Ticket ticket) {
ticketRepository.save(ticket);
    }

    @Override
    public void updateTicket(Ticket ticket) {
ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
ticketRepository.deleteById(id);
    }
}
