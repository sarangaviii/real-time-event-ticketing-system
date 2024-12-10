package com.tharuka.realtimeeventticketingbackend.controller;

import com.tharuka.realtimeeventticketingbackend.config.Configuration;
import com.tharuka.realtimeeventticketingbackend.model.TicketPool;
import com.tharuka.realtimeeventticketingbackend.service.CustomerService;
import com.tharuka.realtimeeventticketingbackend.service.VendorService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api/ticketing")
public class TicketingController {
    private final TicketPool ticketPool;
    private ExecutorService executorService;
    private boolean systemRunning = false;


    public TicketingController(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @PostMapping("/start")
    public String startSystem(@RequestBody Configuration configuration) {
        int totalTickets = configuration.getTotalTickets();
        int maxCapacity = configuration.getMaxCapacity();
        int numVendors = configuration.getNumVendors();
        int vendorRate = configuration.getVendorRate();
        int numCustomers = configuration.getNumCustomers();
        int customerRate = configuration.getCustomerRate();
        if (systemRunning) {
            return "System is already running. Stop the system before starting a new simulation.";
        }

        if (totalTickets <= 0 || maxCapacity <= 0 || numVendors <= 0 || vendorRate <= 0 || numCustomers <= 0 || customerRate <= 0) {
            return "All parameters must be positive integers.";
        }

//        ticketPool.setMaxCapacity(maxCapacity);
        ticketPool.setMaxCapacity(maxCapacity);
        executorService = Executors.newFixedThreadPool(numVendors +numCustomers);

        for(int i = 1; i <= numVendors; i++) {
            executorService.execute(new VendorService(ticketPool, i, vendorRate, totalTickets));

        }

        for (int i = 1; i <= numCustomers; i++) {
            executorService.execute(new CustomerService(ticketPool, i, customerRate));
        }
        systemRunning = true;
        return "System started successfully";

    }

    // Stop the system
    @PostMapping("/stop")
    public String stopSystem() {
        if(executorService != null) {
            executorService.shutdownNow();
        }
        systemRunning = false;
        return "System stopped successfully";
    }

    // Get the status of the system
    @GetMapping("/status")
    public String getStatus() {
        int availableTickets = ticketPool.getAvailableTickets();
        return "Available tickets: " + availableTickets;
    }

    // Reset the system
    @PostMapping("/reset")
    public String resetSystem() {
        if (systemRunning) {
            stopSystem();
        }
        ticketPool.clear();
        return "System reset successfully";
    }


}
