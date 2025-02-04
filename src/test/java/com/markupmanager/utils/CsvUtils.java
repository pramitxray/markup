package com.markupmanager.utils;

import com.markupmanager.models.Customer;
import com.markupmanager.models.Order;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class CsvUtils {
    public static Map<String, Customer> loadCustomers(String filePath) throws Exception {
        Map<String, Customer> customers = new HashMap<>();
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] line;

        while ((line = reader.readNext()) != null) {
            customers.put(line[0], new Customer(line[0], Double.parseDouble(line[1]), line[2]));
        }

        reader.close();
        return customers;
    }

    public static List<Order> loadOrders(String filePath) throws Exception {
        List<Order> orders = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] line;

        while ((line = reader.readNext()) != null) {
            orders.add(new Order(line[0], line[1], line[2], line[3], Integer.parseInt(line[4]), Double.parseDouble(line[5])));
        }

        reader.close();
        return orders;
    }

    public static void saveResults(String filePath, List<String[]> data) throws Exception {
        CSVWriter writer = new CSVWriter(new FileWriter(filePath));
        writer.writeAll(data);
        writer.close();
    }
}
