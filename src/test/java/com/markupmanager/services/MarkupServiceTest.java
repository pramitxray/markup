package com.markupmanager.services;

import com.markupmanager.models.Customer;
import com.markupmanager.models.Order;
import org.junit.Test;
import static org.junit.Assert.*;

public class MarkupServiceTest {

    @Test
    public void testPipsMarkupCalculation() {
        Customer customer = new Customer("Cust1", 40, "pips");
        Order order = new Order("O001", "Cust1", "B", "X", 200, 8.25);
        double markup = MarkupService.calculateMarkup(customer, order);
        assertEquals(80.0, markup, 0.01);
    }

    @Test
    public void testPercentageMarkupCalculation() {
        Customer customer = new Customer("Cust2", 5, "percentage");
        Order order = new Order("O002", "Cust2", "B", "X", 200, 8.25);
        double markup = MarkupService.calculateMarkup(customer, order);
        assertEquals(82.50, markup, 0.01);
    }

    @Test
    public void testPerHundredMarkupCalculation() {
        Customer customer = new Customer("Cust3", 7, "$ per hundred");
        Order order = new Order("O003", "Cust3", "S", "X", 200, 8.25);
        double markup = MarkupService.calculateMarkup(customer, order);
        assertEquals(-115.50, markup, 0.01); // Selling case (negative markup)
    }
}
