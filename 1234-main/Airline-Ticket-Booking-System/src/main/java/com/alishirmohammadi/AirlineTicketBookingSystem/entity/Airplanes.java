package com.alishirmohammadi.AirlineTicketBookingSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "airplanes")
public class Airplanes {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "airplane_id")
    private Long airplaneId;

    @Column(name = "airplane_code")
    private String airplaneCode;

    @Column(name = "airplane_name")
    private String airplaneName;

    @Column(name = "capacity")
    private int capacity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "airlines_id", referencedColumnName = "airline_id")
    private Airlines airlines;

    @OneToOne(targetEntity = Flight.class, mappedBy = "airplanes", cascade =CascadeType.ALL)
    private Flight flight;

    public Long getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(Long airplaneId) {
        this.airplaneId = airplaneId;
    }

    public String getAirplaneCode() {
        return airplaneCode;
    }

    public void setAirplaneCode(String airplaneCode) {
        this.airplaneCode = airplaneCode;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Airlines getAirlines() {
        return airlines;
    }

    public void setAirlines(Airlines airlines) {
        this.airlines = airlines;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getAirplaneName() {
        return airplaneName;
    }

    public void setAirplaneName(String airplaneName) {
        this.airplaneName = airplaneName;
    }

}
