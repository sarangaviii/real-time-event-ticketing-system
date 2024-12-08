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


    // prompt the user for configuration inputs

    public void promptForConfiguration() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter total tickets: ");
        totalTickets = scanner.nextInt();

        System.out.print("Enter maximum ticket pool capacity: ");
        maxCapacity = scanner.nextInt();

        System.out.print("Enter number of vendors: ");
        numVendors = scanner.nextInt();

        System.out.print("Enter vendor ticket release rate (tickets/second): ");
        vendorRate = scanner.nextInt();

        System.out.print("Enter number of customers: ");
        numCustomers = scanner.nextInt();

        System.out.print("Enter customer ticket purchase rate (tickets/second): ");
        customerRate = scanner.nextInt();

    }

    // save the configuration to a JSON file using Jackson
    public void saveToJsonFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), this);
            System.out.println("Configuration saved to " + filePath);
        } catch (IOException e) {
            System.out.print("Error saving configuration to file: " + e.getMessage());
        }
    }

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
