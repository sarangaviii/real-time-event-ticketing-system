package com.tharuka.realtimeeventticketingbackend.model;

import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component // Mark the class as a Spring component or spring bean
public class TicketPool {
    private final List<Integer> tickets = new ArrayList<>();

    @Setter
    private int maxCapacity;

    // add tickets to the pool (thread-safe)
    public synchronized void addTickets(int ticketId) throws InterruptedException { // throw exception means if the pool is full it will throw an exception
        while (tickets.size() >= maxCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
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
                Thread.currentThread().interrupt();
                return -1;
            }
        }
        int ticket = tickets.remove(0);
        notifyAll(); // Notify any waiting threads
        return ticket;
    }

    public synchronized int getAvailableTickets() {
        return tickets.size(); // Number of available tickets return tickets.size();
    }

    public synchronized void clear() {
        tickets.clear();
    }
}