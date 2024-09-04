package com.travel.utils;

public class Destination {
    private int destinationId;
    private String name;
    private String location;
    private String description;
    private double pricePerDay;

    // Getters
    public int getDestinationId() {
        return destinationId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    // Setters
    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
