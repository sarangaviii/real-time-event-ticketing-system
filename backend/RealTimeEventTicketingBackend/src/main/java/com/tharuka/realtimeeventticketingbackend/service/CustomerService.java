package com.tharuka.realtimeeventticketingbackend.service;

import com.tharuka.realtimeeventticketingbackend.model.TicketPool;

public class CustomerService implements Runnable{
    private final TicketPool ticketPool;
    private final int cutomerId;
    private final int ticketPerSecond;

    public CustomerService(TicketPool ticketPool, int cutomerId, int ticketPerSecond) {
        this.ticketPool = ticketPool;
        this.cutomerId = cutomerId;
        this.ticketPerSecond = ticketPerSecond;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int ticket = ticketPool.removeTicket();
                System.out.println("Customer " + cutomerId + " purchased Ticket " + ticket);
                Thread.sleep(1000 / ticketPerSecond);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
