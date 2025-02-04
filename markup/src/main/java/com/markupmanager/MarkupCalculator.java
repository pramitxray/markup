package com.markupmanager;

import com.markupmanager.models.Customer;
import com.markupmanager.models.Order;
import com.markupmanager.services.MarkupService;
import com.markupmanager.utils.CsvUtils;
import java.util.*;

public class MarkupCalculator {
    public static void main(String[] args) throws Exception {
        Map<String, Customer> customers = CsvUtils.loadCustomers("src/main/resources/profiles.csv");
        List<Order> orders = CsvUtils.loadOrders("src/main/resources/orders.csv");

        List<String[]> results = new ArrayList<>();
        results.add(new String[]{"Order ID", "Direction", "Quantity", "Market Rate", "Markup", "Net Amount"});

        for (Order order : orders) {
            Customer customer = customers.get(order.getCustomerId());
            double markup = MarkupService.calculateMarkup(customer, order);
            double netAmount = order.getQuantity() * order.getMarketRate() + markup;

            results.add(new String[]{order.getCustomerId(), order.getDirection(), 
                                     String.valueOf(order.getQuantity()), String.valueOf(order.getMarketRate()),
                                     String.valueOf(markup), String.valueOf(netAmount)});
        }

        CsvUtils.saveResults("src/main/resources/output.csv", results);
        System.out.println("Markup calculations completed. Check output.csv!");
    }
}
