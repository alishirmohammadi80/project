package com.alishirmohammadi.AirlineTicketBookingSystem.repository;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Airlines;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Airplanes;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
   // @Query("SELECT c FROM Customer c WHERE c.fullName LIKE %?1%" + " OR c.nationalCode LIKE %?1%" + " OR c.dateOfBirth LIKE %?1%"
//    +" OR c.email LIKE %?1%"+" OR c.phoneNumber LIKE %?1%"+" OR c.id LIKE %?1%")
    @Query("SELECT p FROM Customer p WHERE CONCAT(p.fullName, ' ', p.nationalCode, ' ', p.dateOfBirth, ' ', p.email, ' ', p.phoneNumber) LIKE %?1%")
    public List<Customer> searchCustomer(String keyword);
}
