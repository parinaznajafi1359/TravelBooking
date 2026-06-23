package com.example.application.service;

import com.example.application.data.TravelOrder;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravelOrderService {

    public void removeAll() {
        orders.clear();
    }

    public ArrayList<TravelOrder> findAll() {
        return new ArrayList<>(orders);
    }

    private ArrayList<TravelOrder> orders;

    public TravelOrderService() {
        orders = new ArrayList<>(1000);
    }

    public List<TravelOrder> getOrders() {
        return orders;
    }

    public void addOrder(TravelOrder order) {
        orders.add(order);
    }

    public void clear() {
        orders.clear();
    }

    public void fillTestData(int anz) {
        TravelOrder order;
        Faker faker = new Faker();

        String[] destinations = {
                "Wien - Italien",
                "Wien - Paris",
                "Wien - Barcelona",
                "Wien - Dubai",
                "Wien - London",
                "Wien - Amsterdam"
        };

        String[] travelClasses = {
                "Economy",
                "Standard",
                "Premium",
                "Luxury"
        };


        for (int i = 0; i < anz; i++) {
            order = new TravelOrder();

            order.setOrderId((long) (i + 1));
            order.setOrderDate(LocalDate.now().minusDays(faker.number().numberBetween(0, 30)));
            order.setDestination(destinations[faker.number().numberBetween(0, destinations.length)]);
            order.setTravelClass(travelClasses[faker.number().numberBetween(0, travelClasses.length)]);
            order.setPrice(faker.number().randomDouble(2, 100, 1200));
            order.setPersons(faker.number().numberBetween(1, 6));
            order.setInsurance(faker.bool().bool());

            orders.add(order);
        }
    }

    @Override
    public String toString() {
        return orders.stream()
                .map(order -> order.toString())
                .collect(Collectors.joining("\n"));
    }
}