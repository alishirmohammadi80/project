package com.alishirmohammadi.AirlineTicketBookingSystem.repository;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
  //  @Query("SELECT b FROM Flight b WHERE b.flightNumber LIKE %?1%" + " OR b.fare LIKE %?1%"
  //  +" OR b.journeyTime LIKE %?1%")
  @Query("SELECT p FROM Flight p WHERE CONCAT(p.flightNumber, ' ', p.fare, ' ', p.journeyTime, ' '," +
          " p.airplanes.airplaneName, ' ', p.airlines.airlineName, ' ', p.sourceStop.name, ' ', p.destStop.name , ' ', p.availableSeats, ' ', p.tripDate, ' ', p.tripTime) LIKE %?1%")
    public List<Flight> searchFlight(String keyword);


  @Query("SELECT p FROM Flight p WHERE CONCAT(p.flightNumber, ' ', p.fare, ' ', p.journeyTime, ' '," +
          " p.airplanes.airplaneName, ' ', p.airlines.airlineName, ' ', p.sourceStop.name, ' ', p.destStop.name , ' ', p.availableSeats, ' ', p.tripDate, ' ', p.tripTime) LIKE %?1%")
  public List<Flight> searchAvailableTickets(String keyword);
}
