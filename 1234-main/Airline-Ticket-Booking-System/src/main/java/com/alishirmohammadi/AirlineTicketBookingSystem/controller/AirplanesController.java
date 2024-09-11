package com.alishirmohammadi.AirlineTicketBookingSystem.controller;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Airlines;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Airplanes;
import com.alishirmohammadi.AirlineTicketBookingSystem.excelExporter.AirlinesExcel;
import com.alishirmohammadi.AirlineTicketBookingSystem.excelExporter.AirplanesExcel;
import com.alishirmohammadi.AirlineTicketBookingSystem.repository.AirlinesRepository;
import com.alishirmohammadi.AirlineTicketBookingSystem.repository.AirplanesRepository;
import com.alishirmohammadi.AirlineTicketBookingSystem.service.AirlinesService;
import com.alishirmohammadi.AirlineTicketBookingSystem.service.AirplanesService;
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
public class AirplanesController {
    @Autowired
    AirplanesService airplanesService;
    @Autowired
    AirlinesService airlinesService;
    @GetMapping("/airplanes")
    public String findAllAirplanes(Model model) {
        List<Airplanes> airplanes = airplanesService.findAllAirplanes();
        model.addAttribute("airplanes", airplanes);
        return "list-airplanes";
    }
    @GetMapping("/searchAirplanes")
    public String searchAirplanes(@Param("keyword") String keyword, Model model) {
        List<Airplanes> airplanes = airplanesService.searchAirplanes(keyword);
        model.addAttribute("airplanes", airplanes);
        model.addAttribute("keyword", keyword);
        return "list-airplanes";
    }

    @GetMapping("/airplane/{id}")
    public String findAirplaneById(@PathVariable("id") Long id, Model model) {
        Airplanes airplanes =airplanesService.findAirplaneById(id);
        model.addAttribute("airplanes", airplanes);
        return "list-airplanes";
    }

    @GetMapping("/addAirplane")
    public String showCreateForm(Airplanes airplanes, Model model) {
        model.addAttribute("airlines", airlinesService.findAllAirlines());
        return "add-airplane";
    }
    @PostMapping("/add-airplane")
    public String createAirplane(Airplanes airplanes, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-airplane";
        }

        airplanesService.saveAirplanes(airplanes);
        model.addAttribute("airplanes", airplanesService.findAllAirplanes());
        return "redirect:/airplanes";
    }

    @GetMapping("/updateAirplane/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Airplanes airplanes = airplanesService.findAirplaneById(id);
        model.addAttribute("airlines", airlinesService.findAllAirlines());
        model.addAttribute("airplanes",airplanes);
        return "update-airplane";
    }
    @PostMapping("/update-airplane/{id}")
    public String updateAirplane(@PathVariable("id") Long id,Airplanes airplanes, Model model) {
//airplanesService.saveAirplanes(airplanes);
        Airplanes existingAirplanes=airplanesService.findAirplaneById(id);
        existingAirplanes.setAirplaneId(id);
        existingAirplanes.setAirplaneName(airplanes.getAirplaneName());
        existingAirplanes.setAirplaneCode(airplanes.getAirplaneCode());
       existingAirplanes.setCapacity(airplanes.getCapacity());
       existingAirplanes.setAirlines(airplanes.getAirlines());
        airplanesService.saveAirplanes(existingAirplanes);
        model.addAttribute("airplanes",airplanesService.findAllAirplanes());
        return "redirect:/airplanes";
    }

    @GetMapping("/remove-airplane/{id}")
    public String deleteAirplane(@PathVariable("id") Long id, Model model) {
        airplanesService.deleteAirplane(id);
        model.addAttribute("airplanes", airplanesService.findAllAirplanes());
        return "redirect:/airplanes";
    }
    @Autowired
    AirplanesRepository repository;
    @GetMapping("/airplanes/export/excel")
    public void exportToExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=airplanes_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<Airplanes> listAirplanes = repository.findAll();
        AirplanesExcel excelExporter = new AirplanesExcel(listAirplanes);
        excelExporter.export(response);
    }
}
