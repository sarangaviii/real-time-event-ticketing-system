package com.tharuka.realtimeeventticketingbackend;

import com.tharuka.realtimeeventticketingbackend.config.Configuration;
import com.tharuka.realtimeeventticketingbackend.model.TicketPool;
import com.tharuka.realtimeeventticketingbackend.service.VendorService;
import com.tharuka.realtimeeventticketingbackend.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class RealTimeEventTicketingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealTimeEventTicketingBackendApplication.class, args);
        System.out.println("Real-Time Event Ticketing System is running...");

        // prompt for configuration inputs
        Configuration config = new Configuration();
        config.promptForConfiguration();

        // save the configuration to a JSON file
        config.saveToJsonFile("configData.json");

        // Initialize the system with the configuration
        TicketPool ticketPool = new TicketPool();
        ticketPool.setMaxCapacity(config.getMaxCapacity());

        // start vendor threads
        Thread[] vendorThreads = new Thread[config.getNumVendors()];
        for (int i = 0; i < config.getNumVendors(); i++) {
            vendorThreads[i] = new Thread(new VendorService(ticketPool, i + 1, config.getVendorRate(), config.getTotalTickets()));
            vendorThreads[i].start();
        }

        // start customer threads
        Thread[] customerThreads = new Thread[config.getNumCustomers()];
        for (int i = 0; i < config.getNumCustomers(); i++) {
            customerThreads[i] = new Thread(new CustomerService(ticketPool, i + 1, config.getCustomerRate()));
            customerThreads[i].start();
        }


    }

}
