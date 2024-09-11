package com.alishirmohammadi.AirlineTicketBookingSystem.controller;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Airplanes;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Customer;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Stop;
import com.alishirmohammadi.AirlineTicketBookingSystem.excelExporter.AirplanesExcel;
import com.alishirmohammadi.AirlineTicketBookingSystem.excelExporter.CustomerExcel;
import com.alishirmohammadi.AirlineTicketBookingSystem.repository.AirplanesRepository;
import com.alishirmohammadi.AirlineTicketBookingSystem.repository.CustomerRepository;
import com.alishirmohammadi.AirlineTicketBookingSystem.service.CustomerService;
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
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping("/customers")
    public String findAllCustomer(Model model) {
        List<Customer> customers =customerService.findAllCustomer();
        model.addAttribute("customers",customers);
        return "list-customers";
    }
    @GetMapping("/searchCustomer")
    public String searchCustomer(@Param("keyword") String keyword, Model model) {
        List<Customer> customers = customerService.searchCustomer(keyword);
        model.addAttribute("customers",customers);
        model.addAttribute("keyword", keyword);
        return "list-customers";
    }

    @GetMapping("/customer/{id}")
    public String findCustomerById(@PathVariable("id") Long id, Model model) {
        Customer customer =customerService.findCustomerById(id);
        model.addAttribute("customer",customer);
        return "list-customers";
    }

    @GetMapping("/addCustomer")
    public String showCreateForm(Customer customer) {
        return "add-customer";
    }
    @PostMapping("/add-customer")
    public String createCustomer(Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-customer";
        }

        customerService.saveCustomer(customer);
        model.addAttribute("customer",customerService.findAllCustomer());
        return "redirect:/customers";
    }

    @GetMapping("/updateCustomer/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Customer customer = customerService.findCustomerById(id);
        model.addAttribute("customer", customer);
        return "update-customer";
    }
    @PostMapping("/update-customer/{id}")
    public String updateCustomer(@PathVariable("id") Long id, Customer customer) {
        Customer existingCustomer=customerService.findCustomerById(id);
       existingCustomer.setId(id);
        existingCustomer.setFullName(customer.getFullName());
       existingCustomer.setNationalCode(customer.getNationalCode());
       existingCustomer.setDateOfBirth(customer.getDateOfBirth());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        customerService.saveCustomer(existingCustomer);
        return "redirect:/customers";
    }

    @GetMapping("/remove-customer/{id}")
    public String deleteCustomer(@PathVariable("id") Long id, Model model) {
       customerService.deleteCustomer(id);
        model.addAttribute("customer",customerService.findAllCustomer());
        return "redirect:/customers";
    }
    @Autowired
    CustomerRepository repository;
    @GetMapping("/customer/export/excel")
    public void exportToExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=customers_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<Customer> listcustomer = repository.findAll();
        CustomerExcel excelExporter = new CustomerExcel(listcustomer);
        excelExporter.export(response);
    }
}

