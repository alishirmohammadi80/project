package com.alishirmohammadi.AirlineTicketBookingSystem.service.impl;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Airplanes;
import com.alishirmohammadi.AirlineTicketBookingSystem.repository.AirplanesRepository;
import com.alishirmohammadi.AirlineTicketBookingSystem.service.AirplanesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AirplanesServiceImpl implements AirplanesService {
    @Autowired
    AirplanesRepository airplanesRepository;
    @Override
    public List<Airplanes> findAllAirplanes() {
        return airplanesRepository.findAll();
    }

    @Override
    public List<Airplanes> searchAirplanes(String keyword) {

        if (keyword != null) {
            return airplanesRepository.searchAirplanes(keyword);
        }
        return airplanesRepository.findAll();
    }

    @Override
    public Airplanes findAirplaneById(Long id) {
        return airplanesRepository.getById(id);
    }

    @Override
    public void saveAirplanes(Airplanes airplanes) {
airplanesRepository.save(airplanes);

    }

    @Override
    public void updateAirplane(Airplanes airplanes) {
airplanesRepository.save(airplanes);
    }

    @Override
    public void deleteAirplane(Long id) {
airplanesRepository.deleteById(id);
    }
}
