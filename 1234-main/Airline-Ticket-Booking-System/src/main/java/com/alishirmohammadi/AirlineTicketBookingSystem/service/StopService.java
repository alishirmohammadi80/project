package com.alishirmohammadi.AirlineTicketBookingSystem.service;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Flight;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Stop;

import java.util.List;

public interface StopService {
    public List<Stop> findAllStop();
    public List<Stop> searchStop(String keyword);
    public Stop findStopById(Long id);
    public void saveStop(Stop Stop);
    public void updateStop(Stop Stop);
    public void deleteStop(Long id);
}
