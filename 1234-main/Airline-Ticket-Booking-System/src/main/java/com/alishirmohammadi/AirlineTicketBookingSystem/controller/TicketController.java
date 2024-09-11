package com.alishirmohammadi.AirlineTicketBookingSystem.controller;

import com.alishirmohammadi.AirlineTicketBookingSystem.emailService.EmailService;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Flight;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Stop;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Ticket;
import com.alishirmohammadi.AirlineTicketBookingSystem.excelExporter.FlightExcel;
import com.alishirmohammadi.AirlineTicketBookingSystem.excelExporter.TicketExcel;
import com.alishirmohammadi.AirlineTicketBookingSystem.repository.FlightRepository;
import com.alishirmohammadi.AirlineTicketBookingSystem.repository.TicketRepository;
import com.alishirmohammadi.AirlineTicketBookingSystem.service.CustomerService;
import com.alishirmohammadi.AirlineTicketBookingSystem.service.FlightService;
import com.alishirmohammadi.AirlineTicketBookingSystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TicketController {
    @Autowired
    EmailService emailService;
    @Autowired
    CustomerService customerService;
    @Autowired
    FlightService flightService;
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    TicketService ticketService;
    @GetMapping("/AvailableTickets")
    public String findAllAvailableTickets(Model model) {
        List<Flight> flights =flightRepository.findAll();
        model.addAttribute("flights",flights);
        return "Available_tickets";
    }
    @GetMapping("/searchAvailableTickets")
    public String searchAvailableTickets(@Param("keyword") String keyword, Model model) {
        List<Flight> flights = flightService.searchFlight(keyword);
        model.addAttribute("flights",flights);
        model.addAttribute("keyword", keyword);
        return "Available_tickets";
    }

    @GetMapping("/searchTicketsSold")
    public String searchTicketsSold(@Param("keyword") String keyword, Model model) {
        List<Ticket> tickets = ticketService.searchTicket(keyword);
        model.addAttribute("tickets",tickets);
        model.addAttribute("keyword", keyword);
        return "tickets_sold";
    }

    @GetMapping("/TicketsSold")
    public String findAllTicketsSold(Model model) {
        List<Ticket> tickets =ticketService.findAllTicket();
        model.addAttribute("tickets",tickets);
        return "tickets_sold";
    }
    @GetMapping("/BookingTickets")
    public String showCreateForm(Ticket ticket,Model model) {
        model.addAttribute("customer", customerService.findAllCustomer());
        model.addAttribute("flight",flightService.findAllFlight());
        return "booking_tickets";
    }
    @PostMapping("/Booking_tickets")
    public String BookingTickets(Ticket ticket, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "booking_tickets";
        }

        ticketService.saveTicket(ticket);
        emailService.send(ticket.getCustomer().getEmail(),"بلیط هواپیما","شماره بلیط:"+ticket.getId()+"\n"+"شماره پرواز:"+ticket.getFlight().getFlightNumber()
                +"\n"+"ایرلاین:"+ticket.getFlight().getAirlines().getAirlineName()+"\n"+"هواپیما:"+ticket.getFlight().getAirplanes().getAirplaneName()+"\n"+"مبدا:"+ticket.getFlight().getSourceStop().getName()+"\n"+
                "مقصد:"+ticket.getFlight().getDestStop().getName()+"\n"+"تاریخ پرواز:"+ticket.getFlight().getTripDate()+"\n"+"ساعت پرواز:"+ticket.getFlight().getTripTime()+"\n"+
                "شماره صندلی:"+ticket.getSeatNumber());
        model.addAttribute("ticket",ticketService.findAllTicket());
        return "redirect:/AvailableTickets";
    }

    @Autowired
    TicketRepository repository;
    @GetMapping("/ticket/export/excel")
    public void exportToExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=tickets_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<Ticket> listTicket = repository.findAll();
        TicketExcel excelExporter = new TicketExcel(listTicket);
        excelExporter.export(response);
    }

}
