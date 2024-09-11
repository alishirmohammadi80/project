package com.alishirmohammadi.AirlineTicketBookingSystem.service.impl;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Customer;
import com.alishirmohammadi.AirlineTicketBookingSystem.repository.CustomerRepository;
import com.alishirmohammadi.AirlineTicketBookingSystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchCustomer(String keyword) {
        if (keyword != null) {
            return customerRepository.searchCustomer(keyword);
        }
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.getById(id);
    }

    @Override
    public void saveCustomer(Customer customer) {
customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
customerRepository.deleteById(id);
    }
}
