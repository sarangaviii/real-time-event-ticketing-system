package com.tharuka.realtimeeventticketingbackend.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private int maxCapacity;
    private int numVendors;
    private int vendorRate; // tickets per second
    private int numCustomers;
    private int customerRate; // tickets per second

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getNumVendors() {
        return numVendors;
    }

    public void setNumVendors(int numVendors) {
        this.numVendors = numVendors;
    }

    public int getVendorRate() {
        return vendorRate;
    }

    public void setVendorRate(int vendorRate) {
        this.vendorRate = vendorRate;
    }

    public int getNumCustomers() {
        return numCustomers;
    }

    public void setNumCustomers(int numCustomers) {
        this.numCustomers = numCustomers;
    }

    public int getCustomerRate() {
        return customerRate;
    }

    public void setCustomerRate(int customerRate) {
        this.customerRate = customerRate;
    }
}
