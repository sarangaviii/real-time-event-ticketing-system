package com.tharuka.realtimeeventticketingbackend.controller;

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


    public TicketingController(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @PostMapping("/start")
    public String startSystem(@RequestParam int totalTickets, @RequestParam int maxCapacity, @RequestParam int numVendors, @RequestParam int vendorRate, @RequestParam int numCustomers, @RequestParam int customerRate) {
        ticketPool.setMaxCapacity(maxCapacity);
        executorService = Executors.newFixedThreadPool(numVendors +numCustomers);

        for(int i = 1; i <= numVendors; i++) {
            executorService.execute(new VendorService(ticketPool, i, vendorRate, totalTickets));

        }

        for (int i = 1; i <= numCustomers; i++) {
            executorService.execute(new CustomerService(ticketPool, i, customerRate));
        }

        return "System started successfully";

    }
    @PostMapping("/stop")
    public String stopSystem() {
        if(executorService != null) {
            executorService.shutdownNow();
        }
        return "System stopped successfully";
    }


}
