package com.tharuka.realtimeeventticketingbackend.model;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private final List<Integer> tickets = new ArrayList<>();
    private int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    // add tickets to the pool (thread-safe)
    public synchronized void addTickets(int ticketId) throws InterruptedException { // throw exception means if the pool is full it will throw an exception
        while (tickets.size() >= maxCapacity) {
            try {
                wait();
            } catch (java.lang.InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        tickets.add(ticketId);
        notifyAll();

    }

    // remove tickets from the pool (thread-safe)
    public synchronized int removeTicket() throws InterruptedException {
        while (tickets.isEmpty()) {
            try {
                wait(); // Wait until a ticket is available
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        int ticket = tickets.remove(0);
        notifyAll(); // Notify any waiting threads
        return ticket;
    }

    public synchronized int getAvailableTickets() {
        return tickets.size(); // Number of available tickets return tickets.size();
    }
}