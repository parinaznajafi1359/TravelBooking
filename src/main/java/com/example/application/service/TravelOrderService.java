package com.example.application.service;

import com.example.application.data.TravelOrder;
import com.example.application.data.TravelOrderException;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravelOrderService {

    private ArrayList<TravelOrder> orders;

    public TravelOrderService() {
        orders = new ArrayList<>(1000);
    }

    public List<TravelOrder> getOrders() {
        return orders;
    }

    public ArrayList<TravelOrder> findAll() {
        return new ArrayList<>(orders);
    }

    public void addOrder(TravelOrder order) {
        orders.add(order);
    }

    public void clear() {
        orders.clear();
    }

    public void removeAll() {
        orders.clear();
    }

    public void addWrongOrder() {
        TravelOrder order = new TravelOrder(
                LocalDate.now(),
                "Wien - Dubai",
                "First Class",
                -20.0,
                1,
                true
        );

        orders.add(order);
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
                "Premium Economy",
                "Business Class",
                "First Class"
        };

        int startId = orders.size() + 1;

        for (int i = 0; i < anz; i++) {
            order = new TravelOrder();

            order.setOrderId((long) (startId + i));
            order.setOrderDate(LocalDate.now().minusDays(faker.number().numberBetween(0, 30)));
            order.setDestination(destinations[faker.number().numberBetween(0, destinations.length)]);
            order.setTravelClass(travelClasses[faker.number().numberBetween(0, travelClasses.length)]);
            order.setPrice(faker.number().randomDouble(2, 100, 1200));
            order.setPersons(faker.number().numberBetween(1, 6));
            order.setInsurance(faker.bool().bool());

            orders.add(order);
        }
    }

    public void removeTravelOrder(Long orderId) {
        if (orderId == null) {
            throw new TravelOrderException("No Order ID!");
        }

        boolean removed = orders.removeIf(order -> orderId.equals(order.getOrderId()));

        if (!removed) {
            throw new TravelOrderException("Order with the ID " + orderId + " not found!");
        }
    }

    public void oneMore(Long orderId) {
        if (orderId == null) {
            throw new TravelOrderException("No Order ID!");
        }

        for (TravelOrder order : orders) {
            if (orderId.equals(order.getOrderId())) {
                order.setPersons(order.getPersons() + 1);
                return;
            }
        }

        throw new TravelOrderException("Order with the ID " + orderId + " not found!");
    }

    @Override
    public String toString() {
        return orders.stream()
                .map(order -> order.toString())
                .collect(Collectors.joining("\n"));
    }
}