package com.tharuka.real_time_event_ticketing_system.model;

public class Ticket {
    private int ticketId;
    private int eventId;
    private int customerId;
    private int vendorId;

    public Ticket(int ticketId, int eventId, int customerId, int vendorId) {
        this.ticketId = ticketId;
        this.eventId = eventId;
        this.customerId = customerId;
        this.vendorId = vendorId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }
}
