package main.java.com.tharuka.realtimeeventticketingbackend.model;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<Integer> tickets = new LinkedList<>();
    private int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTickets(int ticketId) throws InterruptedException {
        while (tickets.size() >= maxCapacity) {
            try {
                wait();
            } catch (java.lang.InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public synchronized int removeTicket() throws InterruptedException {
        while (tickets.isEmpty()) {
            wait();
        }
        int ticket = tickets.poll();
        notifyAll();
        return ticket;
    }

    public synchronized int getAvailableTickets() {
        return tickets.size(); // Number of available tickets return tickets.size();
    }
}