package com.pluralsight.models;

import com.pluralsight.Vehicle;

public abstract class Contract {
    // instance variables
    private String Date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;

    // constructor
    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        Date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    // getters & setters
    public String getDate() {
        return Date;
    }
    
    public void setDate(String date) {
        Date = date;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getCustomerEmail() {
        return customerEmail;
    }
    
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    
    public Vehicle getVehicleSold() {
        return vehicleSold;
    }
    
    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    // abstract derived methods
    public abstract double getTotalPrice(Vehicle vehicleSold);
    
    public abstract double getMonthlyPayment(Vehicle vehicleSold);
}