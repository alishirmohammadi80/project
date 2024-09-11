package com.alishirmohammadi.AirlineTicketBookingSystem.entity;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "airlines")
public class Airlines {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "airline_id", updatable = false, nullable = false)
    private Long airlineId;

    @Column(name = "airline_Code")
    private String airlineCode;

    @Column(name = "name")
    private String airlineName;

    @Column(name = "details")
    private String details;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;


    @OneToMany(targetEntity = Airplanes.class, mappedBy = "airlines", cascade =CascadeType.ALL)
    private List<Airplanes> airplanes = new ArrayList<>();

    public Long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
