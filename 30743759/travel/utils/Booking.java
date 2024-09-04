package com.travel.utils;

import java.util.Date;

public class Booking {
    private int bookingId;
    private int destinationId;
    private int customerId;
    private Date bookingDate;
    private Date startDate;
    private Date endDate;
    private double totalCost;
    private String status;

    // Getters
    public int getBookingId() {
        return bookingId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
