package com.alishirmohammadi.AirlineTicketBookingSystem.controller;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Flight;
import com.alishirmohammadi.AirlineTicketBookingSystem.excelExporter.FlightExcel;
import com.alishirmohammadi.AirlineTicketBookingSystem.repository.*;
import com.alishirmohammadi.AirlineTicketBookingSystem.service.AirlinesService;
import com.alishirmohammadi.AirlineTicketBookingSystem.service.AirplanesService;
import com.alishirmohammadi.AirlineTicketBookingSystem.service.FlightService;
import com.alishirmohammadi.AirlineTicketBookingSystem.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class FlightController {
    @Autowired
    FlightService flightService;
    @Autowired
    AirlinesService airlinesService;
    @Autowired
    AirplanesService airplanesService;
    @Autowired
    StopService stopService;
    @Autowired
    FlightRepository flightRepository;
    @GetMapping("/flights")
    public String findAllFlight(Model model) {
        List<Flight> flights =flightService.findAllFlight();
        model.addAttribute("flights",flights);
        return "list-flights";
    }
    @GetMapping("/searchFlight")
    public String searchFlight(@Param("keyword") String keyword, Model model) {
        List<Flight> flights = flightService.searchFlight(keyword);
        model.addAttribute("flights",flights);
        model.addAttribute("keyword", keyword);
        return "list-flights";
    }



    @GetMapping("/flight/{id}")
    public String findFlightById(@PathVariable("id") Long id, Model model) {
        Flight flight =flightService.findFlightById(id);
        model.addAttribute("flight",flight);
        return "list-flights";
    }

    @GetMapping("/addFlight")
    public String showCreateForm(Flight flight,Model model) {
        model.addAttribute("airlines", airlinesService.findAllAirlines());
        model.addAttribute("airplanes",airplanesService.findAllAirplanes());
        model.addAttribute("sourceStop", stopService.findAllStop());
        model.addAttribute("destStop", stopService.findAllStop());
        return "add-flight";
    }
    @PostMapping("/add-flight")
    public String createFlight(Flight flight, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-flight";
        }

       flightService.saveFlight(flight);
        model.addAttribute("flight",flightService.findAllFlight());
        return "redirect:/flights";
    }

    @GetMapping("/updateFlight/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Flight flight = flightService.findFlightById(id);
        model.addAttribute("airlines", airlinesService.findAllAirlines());
        model.addAttribute("airplanes",airplanesService.findAllAirplanes());
        model.addAttribute("sourceStop", stopService.findAllStop());
        model.addAttribute("destStop", stopService.findAllStop());
        model.addAttribute("flight", flight);
        return "update-flight";
    }

   @PostMapping("/update-flight/{id}")
    public String updateFlight(@PathVariable("id") Long id,Flight flight) {
        Flight existingFlight=flightService.findFlightById(id);
       existingFlight.setId(id);
       existingFlight.setFare(flight.getFare());
        existingFlight.setFlightNumber(flight.getFlightNumber());
        existingFlight.setAirlines(flight.getAirlines());
        existingFlight.setAirplanes(flight.getAirplanes());
        existingFlight.setJourneyTime(flight.getJourneyTime());
        existingFlight.setSourceStop(flight.getSourceStop());
        existingFlight.setDestStop(flight.getDestStop());
        flightService.saveFlight(existingFlight);
      //  model.addAttribute("flight", flightService.findAllFlight());
        return "redirect:/flights";
    }

    @GetMapping("/remove-flight/{id}")
    public String deleteFlight(@PathVariable("id") Long id, Model model) {
       flightService.deleteFlight(id);
        model.addAttribute("flight",flightService.findAllFlight());
        return "redirect:/flights";
    }
    @Autowired
    FlightRepository repository;
    @GetMapping("/flight/export/excel")
    public void exportToExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=flights_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<Flight> listFlight = repository.findAll();
        FlightExcel excelExporter = new FlightExcel(listFlight);
        excelExporter.export(response);
    }
}
