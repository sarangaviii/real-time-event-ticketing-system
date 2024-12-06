package com.tharuka.realtimeeventticketingbackend.service;

import com.tharuka.realtimeeventticketingbackend.model.TicketPool;
import org.springframework.stereotype.Service;


public class VendorService implements Runnable {
    private final TicketPool ticketPool;
    private final int vendorId;
    private final int ticketsPerSecond;
    private final int totalTickets;
    public static int ticketCounter = 1;

    public VendorService(TicketPool ticketPool, int vendorId, int ticketsPerSecond, int totalTickets) {
        this.ticketPool = ticketPool;
        this.vendorId = vendorId;
        this.ticketsPerSecond = ticketsPerSecond;
        this.totalTickets = totalTickets;
    }

    @Override
    public void run() {
        try {
            while (ticketCounter <= totalTickets) { // Loop until all tickets are added
                synchronized (VendorService.class) {
                    if (ticketCounter <= totalTickets) { // Check if all tickets have been added
                        ticketPool.addTickets(ticketCounter++);
                        System.out.println("Vendor " + vendorId + " added Ticket " + (ticketCounter - 1));
                    }
                }
                Thread.sleep(1000 / ticketsPerSecond); // Sleep for 1 second
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
