package com.example.application.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Table(name = "travel_order")
public class TravelOrder implements Cloneable {

    @Id
    @Column(name = "order_id")
    private Long orderId;

    @NotNull(message = "Order date is required")
    @PastOrPresent(message = "Order date must be today or in the past")
    @Column(name = "order_date")
    private LocalDate orderDate;

    @NotBlank(message = "Destination is required")
    private String destination;

    @NotBlank(message = "Travel class is required")
    @Pattern(
            regexp = "Economy|Standard|Premium|Luxury",
            message = "Travel class must be Economy, Standard, Premium, or Luxury"
    )
    @Column(name = "travel_class")
    private String travelClass;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "50.0", message = "The min. price is 50.0 Eur")
    @DecimalMax(value = "5000.0", message = "The max. price is 5000.0 Eur")
    private Double price;

    @NotNull(message = "Persons is required")
    @Min(value = 1, message = "At least 1 person is required")
    @Max(value = 10, message = "Maximum 10 persons are allowed")
    private Integer persons;

    @NotNull(message = "Insurance is required")
    private Boolean insurance;

    private static final AtomicLong sequence = new AtomicLong(1000);

    private static final String[] travelClasses = {
            "Economy", "Standard", "Premium", "Luxury"
    };

    public TravelOrder() {
    }

    public TravelOrder(LocalDate orderDate, String destination, String travelClass,
                       Double price, Integer persons, Boolean insurance) {
        setOrderId();
        setOrderDate(orderDate);
        setDestination(destination);
        setTravelClass(travelClass);
        setPrice(price);
        setPersons(persons);
        setInsurance(insurance);
    }

    public TravelOrder(Long orderId, LocalDate orderDate, String destination, String travelClass,
                       Double price, Integer persons, Boolean insurance) {
        setOrderId(orderId);
        setOrderDate(orderDate);
        setDestination(destination);
        setTravelClass(travelClass);
        setPrice(price);
        setPersons(persons);
        setInsurance(insurance);
    }

    public void setOrderId() {
        this.orderId = sequence.getAndIncrement();
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(String travelClass) {
        if (!Arrays.asList(travelClasses).contains(travelClass)) {
            throw new TravelOrderException(
                    "Wrong travel class. Must be: " + Arrays.toString(travelClasses)
            );
        }

        this.travelClass = travelClass;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if (price == null) {
            throw new TravelOrderException("Price must not be null");
        }

        if (price < 50) {
            throw new TravelOrderException("The min. price is 50.0 Eur");
        }

        if (price > 5000) {
            throw new TravelOrderException("The max. price is 5000.0 Eur");
        }

        this.price = price;
    }

    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        if (persons == null) {
            throw new TravelOrderException("Persons must not be null");
        }

        if (persons < 1) {
            throw new TravelOrderException("At least one person is required");
        }

        if (persons > 10) {
            throw new TravelOrderException("Maximum 10 persons are allowed");
        }

        this.persons = persons;
    }

    public Boolean getInsurance() {
        return insurance;
    }

    public void setInsurance(Boolean insurance) {
        this.insurance = insurance;
    }

    @Override
    public TravelOrder clone() {
        return new TravelOrder(
                orderId,
                orderDate,
                destination,
                travelClass,
                price,
                persons,
                insurance
        );
    }

    @Override
    public String toString() {
        return "TravelOrder{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", destination='" + destination + '\'' +
                ", travelClass='" + travelClass + '\'' +
                ", price=" + price +
                ", persons=" + persons +
                ", insurance=" + insurance +
                '}';
    }
}