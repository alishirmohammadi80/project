package com.alishirmohammadi.AirlineTicketBookingSystem.repository;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Airlines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlinesRepository extends JpaRepository<Airlines,Long> {
    @Query("SELECT a FROM Airlines a WHERE a.airlineName LIKE %?1%" + " OR a.airlineCode LIKE %?1%" + " OR a.details LIKE %?1%")
    public List<Airlines> searchAirlines(String keyword);
}
