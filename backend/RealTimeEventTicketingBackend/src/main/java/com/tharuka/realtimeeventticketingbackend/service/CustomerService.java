package com.tharuka.realtimeeventticketingbackend.service;

import com.tharuka.realtimeeventticketingbackend.model.TicketPool;
import org.springframework.stereotype.Service;



public class CustomerService implements Runnable{
    private final TicketPool ticketPool;
    private final int customerId;
    private final int ticketPerSecond;

    public CustomerService(TicketPool ticketPool, int customerId, int ticketPerSecond) {
        this.ticketPool = ticketPool;
        this.customerId = customerId;
        this.ticketPerSecond = ticketPerSecond;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                int ticket = ticketPool.removeTicket();
                if (ticket == -1) {
                    break;
                }
                System.out.println("Customer " + customerId + " purchased Ticket " + ticket);
                Thread.sleep(1000 / ticketPerSecond);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
