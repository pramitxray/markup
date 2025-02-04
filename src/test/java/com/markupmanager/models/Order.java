package com.markupmanager.models;

public class Order {
    private String id;
    private String customerId;
    private String direction; // B (Buy) or S (Sell)
    private String securityCode;
    private int quantity;
    private double marketRate;

    public Order(String id, String customerId, String direction, String securityCode, int quantity, double marketRate) {
        this.id = id;
        this.customerId = customerId;
        this.direction = direction;
        this.securityCode = securityCode;
        this.quantity = quantity;
        this.marketRate = marketRate;
    }

    public String getCustomerId() { return customerId; }
    public String getDirection() { return direction; }
    public int getQuantity() { return quantity; }
    public double getMarketRate() { return marketRate; }
}
