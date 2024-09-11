package com.alishirmohammadi.AirlineTicketBookingSystem.repository;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Airplanes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AirplanesRepository extends JpaRepository<Airplanes,Long> {
   // @Query("SELECT b FROM Airplanes b WHERE b.airplaneName LIKE %?1%" + " OR b.airplaneCode LIKE %?1%" + " OR b.capacity LIKE %?1%")
    //public List<Airplanes> searchAirplanes(String keyword);
    //@Query("SELECT p FROM Airplanes p WHERE CONCAT(p.airplaneCode, p.airplaneName, p.capacity) LIKE %?1%")
   @Query("SELECT p FROM Airplanes p WHERE CONCAT(p.airplaneName, ' ', p.airplaneCode, ' ', p.capacity) LIKE %?1%")
    public List<Airplanes> searchAirplanes(String keyword);

}
