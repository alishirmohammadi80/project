package com.alishirmohammadi.AirlineTicketBookingSystem.service;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Airlines;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Airplanes;

import java.util.List;

public interface AirlinesService {
    public List<Airlines> findAllAirlines();
    public List<Airlines> searchAirlines(String keyword);
    public Airlines findAirlinesById(Long id);
    public void saveAirlines(Airlines airlines);
    public void updateAirlines(Airlines airlines);
    public void deleteAirlines(Long id);
}
