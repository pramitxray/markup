package com.markupmanager.services;

import com.markupmanager.models.Customer;
import com.markupmanager.models.Order;

public class MarkupService {
    public static double calculateMarkup(Customer customer, Order order) {
        double basePrice = order.getQuantity() * order.getMarketRate();
        double markup = 0;

        switch (customer.getMarkupMode().toLowerCase()) {
            case "pips":
                markup = order.getQuantity() * customer.getMagnitude() * 0.01;
                break;
            case "percentage":
                markup = basePrice * (customer.getMagnitude() / 100);
                break;
            case "$ per hundred":
                markup = basePrice * (customer.getMagnitude() / 100);
                break;
        }

        return order.getDirection().equalsIgnoreCase("B") ? markup : -markup;
    }
}
