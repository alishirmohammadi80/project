package com.alishirmohammadi.AirlineTicketBookingSystem.repository;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    @Query("SELECT p FROM Ticket p WHERE CONCAT(p.flight.flightNumber, ' ', p.flight.fare, ' ', p.flight.journeyTime, ' '," +
            " p.flight.airplanes.airplaneName, ' ', p.flight.airlines.airlineName, ' ', p.flight.sourceStop.name, ' ', p.flight.destStop.name , ' ', " +
            "p.flight.availableSeats, ' ', p.flight.tripDate, ' ', p.flight.tripTime , ' ', p.customer.nationalCode , ' ', p.seatNumber) LIKE %?1%")
    public List<Ticket> searchTicket(String keyword);
}
