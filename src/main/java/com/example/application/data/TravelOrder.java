package com.example.application.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "orderId")
@Entity
@Table(name = "travel_order")
public class TravelOrder implements Cloneable {

    @Id
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_date")
    private LocalDate orderDate;

    private String destination;

    @Column(name = "travel_class")
    private String travelClass;

    private Double price;

    private Integer persons;

    private Boolean insurance;

    private static final AtomicLong sequence = new AtomicLong(1000);

    private static final String[] travelClasses = {
            "Economy", "Standard", "Premium", "Luxury"
    };

    public TravelOrder() {
        setOrderId();
        setOrderDate(LocalDate.now());
        setDestination("Wien - Italien");
        setTravelClass("Standard");
        setPrice(199.0);
        setPersons(1);
        setInsurance(true);
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

    public void setTravelClass(String travelClass) {
        if (!Arrays.asList(travelClasses).contains(travelClass)) {
            throw new TravelOrderException(
                    "Wrong travel class. Must be: " + Arrays.toString(travelClasses)
            );
        }

        this.travelClass = travelClass;
    }

    @Override
    public TravelOrder clone() {
        return new TravelOrder(orderId, orderDate, destination, travelClass, price, persons, insurance);
    }
}