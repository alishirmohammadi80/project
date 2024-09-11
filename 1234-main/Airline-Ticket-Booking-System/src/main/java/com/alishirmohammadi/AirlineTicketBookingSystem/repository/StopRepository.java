package com.alishirmohammadi.AirlineTicketBookingSystem.repository;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface StopRepository extends JpaRepository<Stop,Long> {
   // @Query("SELECT  a FROM Stop a WHERE a.name LIKE %?1%" + " OR a.code LIKE %?1%" + " OR a.code LIKE %?1%"+" OR a.detail LIKE %?1%")
   @Query("SELECT p FROM Stop p WHERE CONCAT(p.name, ' ', p.id, ' ', p.code, ' ', p.detail) LIKE %?1%")
    public List<Stop> searchStop(String keyword);
}
