package com.example.application.service;

import com.example.application.data.TravelOrder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TravelOrderServiceTest {

    @Test
    public void testFillTestData() {
        TravelOrderService service = new TravelOrderService();

        service.fillTestData(10);

        assertEquals(10, service.getOrders().size());

        System.out.println(service);
    }

    @Test
    public void testAddOrder() {
        TravelOrderService service = new TravelOrderService();
        TravelOrder order = new TravelOrder();

        service.addOrder(order);

        assertEquals(1, service.getOrders().size());
    }

    @Test
    public void testClear() {
        TravelOrderService service = new TravelOrderService();

        service.fillTestData(5);
        assertEquals(5, service.getOrders().size());

        service.clear();
        assertEquals(0, service.getOrders().size());
    }
}