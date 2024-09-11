package com.alishirmohammadi.AirlineTicketBookingSystem.service.impl;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Stop;
import com.alishirmohammadi.AirlineTicketBookingSystem.repository.StopRepository;
import com.alishirmohammadi.AirlineTicketBookingSystem.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StopServiceImpl implements StopService {
    @Autowired
    StopRepository stopRepository;
    @Override
    public List<Stop> findAllStop() {
        return stopRepository.findAll();
    }

    @Override
    public List<Stop> searchStop(String keyword) {
        if (keyword != null) {
            return stopRepository.searchStop(keyword);
        }
        return stopRepository.findAll();
    }

    @Override
    public Stop findStopById(Long id) {
        return stopRepository.getById(id);
    }

    @Override
    public void saveStop(Stop Stop) {
stopRepository.save(Stop);
    }

    @Override
    public void updateStop(Stop Stop) {
stopRepository.save(Stop);
    }

    @Override
    public void deleteStop(Long id) {
        stopRepository.getById(id);
    }
}
