package com.example.application.data;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TravelOrderTest {

    @Test
    public void testGetDestination() {
        TravelOrder o1 = new TravelOrder();
        TravelOrder o2 = new TravelOrder();

        o1.setDestination("Wien - Paris");
        o2.setDestination("Wien - Barcelona");

        assertEquals("Wien - Paris", o1.getDestination());
        assertEquals("Wien - Barcelona", o2.getDestination());
    }

    @Test
    public void testDefaultConstructor() {
        TravelOrder order = new TravelOrder();

        assertNotNull(order.getOrderId());
        assertEquals(LocalDate.now(), order.getOrderDate());
        assertEquals("Wien - Italien", order.getDestination());
        assertEquals("Standard", order.getTravelClass());
        assertEquals(199.0, order.getPrice());
        assertEquals(1, order.getPersons());
        assertTrue(order.getInsurance());
    }

    @Test
    public void testInvalidPriceTooLow() {
        TravelOrder order = new TravelOrder();

        assertThrows(TravelOrderException.class, () -> {
            order.setPrice(20.0);
        });
    }

    @Test
    public void testInvalidTravelClass() {
        TravelOrder order = new TravelOrder();

        assertThrows(TravelOrderException.class, () -> {
            order.setTravelClass("Super Cheap");
        });
    }

    @Test
    public void testClone() {
        TravelOrder original = new TravelOrder(
                LocalDate.now(),
                "Wien - Dubai",
                "Luxury",
                599.0,
                2,
                true
        );

        TravelOrder clone = original.clone();

        assertEquals(original.getOrderId(), clone.getOrderId());
        assertEquals(original.getDestination(), clone.getDestination());
        assertEquals(original.getTravelClass(), clone.getTravelClass());
        assertEquals(original.getPrice(), clone.getPrice());
        assertEquals(original.getPersons(), clone.getPersons());
        assertEquals(original.getInsurance(), clone.getInsurance());
    }
}