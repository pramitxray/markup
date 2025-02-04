package com.markupmanager;

import com.markupmanager.models.Customer;
import com.markupmanager.models.Order;
import com.markupmanager.services.MarkupService;
import com.markupmanager.utils.CsvUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class MarkupCalculator {
    private static final Logger logger = LogManager.getLogger(MarkupCalculator.class);

    public static void main(String[] args) throws Exception {
        logger.info("Application started...");

        try {
            Map<String, Customer> customers = CsvUtils.loadCustomers("src/main/resources/profiles.csv");
            List<Order> orders = CsvUtils.loadOrders("src/main/resources/orders.csv");

            logger.info("Loaded {} customers and {} orders", customers.size(), orders.size());

            for (Order order : orders) {
                Customer customer = customers.get(order.getCustomerId());
                double markup = MarkupService.calculateMarkup(customer, order);
                double netAmount = order.getQuantity() * order.getMarketRate() + markup;

                logger.info("Processed Order ID: {}, Net Amount: {}", order.getCustomerId(), netAmount);
            }

            logger.info("All orders processed successfully.");
        } catch (Exception e) {
            logger.error("An error occurred: ", e);
        }
    }
}
