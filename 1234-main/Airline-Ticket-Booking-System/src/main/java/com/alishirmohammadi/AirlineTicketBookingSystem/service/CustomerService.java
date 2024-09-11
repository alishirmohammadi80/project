package com.alishirmohammadi.AirlineTicketBookingSystem.service;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Airplanes;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public List<Customer> findAllCustomer();
    public List<Customer> searchCustomer(String keyword);
    public Customer findCustomerById(Long id);
    public void saveCustomer(Customer customer);
    public void updateCustomer(Customer customer);
    public void deleteCustomer(Long id);
}
