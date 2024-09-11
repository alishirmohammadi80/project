package com.alishirmohammadi.AirlineTicketBookingSystem.service.impl;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Airlines;
import com.alishirmohammadi.AirlineTicketBookingSystem.repository.AirlinesRepository;
import com.alishirmohammadi.AirlineTicketBookingSystem.service.AirlinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AirlinesServiceImpl implements AirlinesService {
    @Autowired
    AirlinesRepository airlinesRepository;
    @Override
    public List<Airlines> findAllAirlines() {
        return airlinesRepository.findAll();
    }

    @Override
    public List<Airlines> searchAirlines(String keyword) {
        if (keyword != null) {
            return airlinesRepository.searchAirlines(keyword);
        }
        return airlinesRepository.findAll();
    }


    @Override
    public Airlines findAirlinesById(Long id) {
        return airlinesRepository.getById(id);
    }

    @Override
    public void saveAirlines(Airlines airlines) {
airlinesRepository.save(airlines);
    }

    @Override
    public void updateAirlines(Airlines airlines) {
airlinesRepository.save(airlines);
    }

    @Override
    public void deleteAirlines(Long id) {

        airlinesRepository.deleteById(id);
    }
}
