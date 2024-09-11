package com.alishirmohammadi.AirlineTicketBookingSystem.controller;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Stop;
import com.alishirmohammadi.AirlineTicketBookingSystem.excelExporter.StopExcel;
import com.alishirmohammadi.AirlineTicketBookingSystem.repository.StopRepository;
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
public class StopController {
    @Autowired
    StopService stopService;
    @GetMapping("/stop")
    public String findAllStop(Model model) {
        List<Stop> stops =stopService.findAllStop();
        model.addAttribute("stops",stops);
        return "list-stop";
    }
    @GetMapping("/searchStop")
    public String searchStop(@Param("keyword") String keyword, Model model) {
        List<Stop> stops = stopService.searchStop(keyword);
        model.addAttribute("stops",stops);
        model.addAttribute("keyword", keyword);
        return "list-stop";
    }
    @GetMapping("/stops/{id}")
    public String findStopById(@PathVariable("id") Long id, Model model) {
        Stop stop =stopService.findStopById(id);
        model.addAttribute("stop",stop);
        return "list-stop";
    }

    @GetMapping("/addStop")
    public String showCreateForm(Stop stop) {
        return "add-stop";
    }
    @PostMapping("/add-stop")
    public String createstop(Stop stop, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-stop";
        }
        stopService.saveStop(stop);
        model.addAttribute("stop", stopService.findAllStop());
        return "redirect:/stop";
    }

    @GetMapping("/updateStop/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Stop stop = stopService.findStopById(id);
        model.addAttribute("stop", stop);
        return "update-stop";
    }
    @PostMapping("/update-stop/{id}")
    public String updateStop(@PathVariable("id") Long id, Stop stop) {
       Stop existingStop=stopService.findStopById(id);
        existingStop.setId(id);
        existingStop.setName(stop.getName());
        existingStop.setCode(stop.getCode());
        existingStop.setDetail(stop.getDetail());
      stopService.saveStop(existingStop);
        return "redirect:/stop";
    }

    @GetMapping("/remove-Stop/{id}")
    public String deleteStop(@PathVariable("id") Long id, Model model) {
       stopService.deleteStop(id);
        model.addAttribute("stop",stopService.findAllStop());
        return "redirect:/stop";
    }
    @Autowired
    StopRepository repository;
    @GetMapping("/stop/export/excel")
    public void exportToExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Stops_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<Stop> listStop = repository.findAll();
        StopExcel excelExporter = new StopExcel(listStop);
        excelExporter.export(response);
    }
}
