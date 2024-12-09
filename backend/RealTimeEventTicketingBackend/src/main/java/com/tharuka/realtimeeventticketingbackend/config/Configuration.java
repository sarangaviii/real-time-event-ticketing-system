package com.tharuka.realtimeeventticketingbackend.config;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Setter
@Getter
public class Configuration {
    // getters and setters
    private int totalTickets;
    private int maxCapacity;
    private int numVendors;
    private int vendorRate; // tickets per second
    private int numCustomers;
    private int customerRate; // tickets per second



    // prompt the user for configuration inputs
    public void promptForConfiguration() {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object

        totalTickets = getPositiveInteger(scanner, "Enter total tickets: ");

        maxCapacity = getPositiveInteger(scanner, "Enter maximum ticket pool capacity: ");

        numVendors = getPositiveInteger(scanner, "Enter number of vendors: ");

        vendorRate = getPositiveInteger(scanner, "Enter vendor ticket release rate (tickets/second): ");

        numCustomers = getPositiveInteger(scanner, "Enter number of customers: ");

        customerRate = getPositiveInteger(scanner, "Enter customer ticket purchase rate (tickets/second): ");

    }

    //A Method to validate the configuration
    public int getPositiveInteger(Scanner scanner, String prompt) {
        int value = -1; // Initialize to an invalid value
        while (value <= 0) { // Loop until a positive integer is entered
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine()); // Parse the input as an integer
                if (value <= 0) {
                    System.out.println("Value must be positive. Please enter a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
            }
        }
        return value;
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


}
