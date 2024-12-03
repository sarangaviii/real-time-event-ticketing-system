package com.tharuka.realtimeeventticketingbackend.model;

public class Vendor {
    private int id; // gives a unique id to each vendor

    public Vendor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}