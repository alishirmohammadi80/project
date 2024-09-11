package com.alishirmohammadi.AirlineTicketBookingSystem.service;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Airplanes;
import java.util.List;
public interface AirplanesService {
    public List<Airplanes> findAllAirplanes();
    public List<Airplanes> searchAirplanes(String keyword);
    public Airplanes findAirplaneById(Long id);
    public void saveAirplanes(Airplanes airplanes);
    public void updateAirplane(Airplanes airplanes);
    public void deleteAirplane(Long id);
}
