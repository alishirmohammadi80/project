package com.alishirmohammadi.AirlineTicketBookingSystem.controller;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Airlines;
import com.alishirmohammadi.AirlineTicketBookingSystem.excelExporter.AirlinesExcel;
import com.alishirmohammadi.AirlineTicketBookingSystem.repository.AirlinesRepository;
import com.alishirmohammadi.AirlineTicketBookingSystem.service.AirlinesService;
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
public class AirlinesController {
    @Autowired
    AirlinesService airlinesService;

    @GetMapping("/airlines")
    public String findAllAirlines(Model model) {
        List<Airlines> airlines = airlinesService.findAllAirlines();
        model.addAttribute("airlines", airlines);
        return "list-airlines";
    }
    @GetMapping("/searchAirlines")
    public String searchAirlines(@Param("keyword") String keyword, Model model) {
        List<Airlines> airlines = airlinesService.searchAirlines(keyword);
        model.addAttribute("airlines", airlines);
        model.addAttribute("keyword", keyword);
        return "list-airlines";
    }

    @GetMapping("/airline/{id}")
    public String findAirlineById(@PathVariable("id") Long id, Model model) {
       Airlines airlines =airlinesService.findAirlinesById(id);
        model.addAttribute("airlines", airlines);
        return "list-airlines";
    }

    @GetMapping("/addAirline")
    public String showCreateForm(Airlines airlines) {
        return "add-airline";
    }
    @PostMapping("/add-airline")
    public String createAirline(Airlines airlines, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-airline";
        }

       airlinesService.saveAirlines(airlines);
        model.addAttribute("airlines", airlinesService.findAllAirlines());
        return "redirect:/airlines";
    }

    @GetMapping("/updateAirline/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Airlines airlines = airlinesService.findAirlinesById(id);
        model.addAttribute("airlines", airlines);
        return "update-airline";
    }
    @PostMapping("/update-airline/{id}")
    public String updateAirline(@PathVariable("id") Long id, Airlines airlines) {
      Airlines existingAirline=airlinesService.findAirlinesById(id);
      existingAirline.setAirlineId(id);
      existingAirline.setAirlineName(airlines.getAirlineName());
        existingAirline.setAirlineCode(airlines.getAirlineCode());
      existingAirline.setDetails(airlines.getDetails());
       airlinesService.saveAirlines(existingAirline);

        return "redirect:/airlines";
    }

    @GetMapping("/remove-airline/{id}")
    public String deleteAirline(@PathVariable("id") Long id, Model model) {
       airlinesService.deleteAirlines(id);
        model.addAttribute("airlines", airlinesService.findAllAirlines());
        return "redirect:/airlines";
    }
    @Autowired
    AirlinesRepository repository;
    @GetMapping("/airlines/export/excel")
    public void exportToExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=airlines_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<Airlines> listAirlines = repository.findAll();
        AirlinesExcel excelExporter = new AirlinesExcel(listAirlines);
        excelExporter.export(response);
    }
}
