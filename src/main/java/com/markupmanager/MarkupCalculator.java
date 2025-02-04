package com.markupmanager;

import com.markupmanager.models.Customer;
import com.markupmanager.models.Order;
import com.markupmanager.services.MarkupService;
import com.markupmanager.utils.CsvUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarkupCalculator {
    private static final Logger logger = LogManager.getLogger(MarkupCalculator.class);

    public static void main(String[] args) {
        logger.info("Application started...");

        try {
            Map<String, Customer> customers = CsvUtils.loadCustomers("src/main/resources/profiles.csv");
            List<Order> orders = CsvUtils.loadOrders("src/main/resources/orders.csv");

            if (customers.isEmpty()) {
                logger.warn("No customers loaded. Check profiles.csv.");
                System.out.println("No customers loaded.");
                return;
            }
            if (orders.isEmpty()) {
                logger.warn("No orders loaded. Check orders.csv.");
                System.out.println("No orders loaded.");
                return;
            }

            logger.info("Loaded {} customers and {} orders.", customers.size(), orders.size());

            List<String[]> results = new ArrayList<>();
            results.add(new String[]{"Order ID", "Direction", "Quantity", "Market Rate", "Markup", "Net Amount"});

            for (Order order : orders) {
                Customer customer = customers.get(order.getCustomerId());

                if (customer == null) {
                    logger.warn("Customer ID {} not found in profiles.csv. Skipping order {}.", order.getCustomerId(), order.getId());
                    continue;
                }

                double markup = MarkupService.calculateMarkup(customer, order);
                double netAmount = order.getQuantity() * order.getMarketRate() + markup;

                results.add(new String[]{order.getId(), order.getDirection(),
                        String.valueOf(order.getQuantity()), String.valueOf(order.getMarketRate()),
                        String.format("%.2f", markup), String.format("%.2f", netAmount)});

                logger.info("Processed Order ID: {}, Net Amount: {:.2f}", order.getId(), netAmount);
            }

            // Write results to output.csv
            CsvUtils.saveResults("src/main/resources/output.csv", results);
            logger.info("All orders processed successfully.");
            System.out.println("All orders processed successfully.");  // ✅ Ensure this message is printed

        } catch (Exception e) {
            logger.error("An error occurred: ", e);
            System.out.println("Application error occurred.");
        }
    }
}
