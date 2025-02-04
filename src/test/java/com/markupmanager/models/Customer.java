package com.markupmanager.models;

public class Customer {
    private String id;
    private double magnitude;
    private String markupMode;

    public Customer(String id, double magnitude, String markupMode) {
        this.id = id;
        this.magnitude = magnitude;
        this.markupMode = markupMode;
    }

    public String getId() { return id; }
    public double getMagnitude() { return magnitude; }
    public String getMarkupMode() { return markupMode; }
}
