package com.alishirmohammadi.AirlineTicketBookingSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "flightNumber")
    private int flightNumber;

    @Column(name = "fare")
    private int fare;

    @Column(name = "journey_time")
    private int journeyTime;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "airplanes_id", referencedColumnName = "airplane_id")
    private Airplanes airplanes;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "airlines_id", referencedColumnName = "airline_id")
    private Airlines airlines;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "source_stop_id", referencedColumnName = "id")
    private Stop sourceStop;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dest_stop_id", referencedColumnName = "id")
    private Stop destStop;

    @Column(name = "trip_date")
    private Date tripDate;

    private java.sql.Time tripTime;
    @Column(name = "available_seats")
    private int availableSeats;

    @OneToMany(targetEntity = Ticket.class, mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Ticket> ticketsSold = new ArrayList<>();

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public Time getTripTime() {
        return tripTime;
    }

    public void setTripTime(Time tripTime) {
        this.tripTime = tripTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public List<Ticket> getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(List<Ticket> ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public int getJourneyTime() {
        return journeyTime;
    }

    public void setJourneyTime(int journeyTime) {
        this.journeyTime = journeyTime;
    }

    public Airplanes getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(Airplanes airplanes) {
        this.airplanes = airplanes;
    }

    public Airlines getAirlines() {
        return airlines;
    }

    public void setAirlines(Airlines airlines) {
        this.airlines = airlines;
    }

    public Stop getSourceStop() {
        return sourceStop;
    }

    public void setSourceStop(Stop sourceStop) {
        this.sourceStop = sourceStop;
    }

    public Stop getDestStop() {
        return destStop;
    }

    public void setDestStop(Stop destStop) {
        this.destStop = destStop;
    }

}
