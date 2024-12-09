package com.tharuka.realtimeeventticketingbackend;

import com.tharuka.realtimeeventticketingbackend.config.Configuration;
import com.tharuka.realtimeeventticketingbackend.model.TicketPool;
import com.tharuka.realtimeeventticketingbackend.service.VendorService;
import com.tharuka.realtimeeventticketingbackend.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;


@SpringBootApplication
public class RealTimeEventTicketingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealTimeEventTicketingBackendApplication.class, args);

    }

    @Bean
    public CommandLineRunner runSimulation() {
        return args -> {
            while (true) {

                System.out.println("Real-Time Event Ticketing System is running...");
                // prompt for configuration inputs
                Configuration config = new Configuration();
                config.promptForConfiguration();

                // save the configuration to a JSON file
                config.appendToJsonFile("configData.json");

                System.out.println("System started successfully.");

                // Initialize the system with the configuration
                TicketPool ticketPool = new TicketPool();
                ticketPool.setMaxCapacity(config.getMaxCapacity());

                // start vendor threads
                Thread[] vendorThreads = new Thread[config.getNumVendors()];
                for (int i = 0; i < config.getNumVendors(); i++) {
                    vendorThreads[i] = new Thread(new VendorService(ticketPool, i + 1, config.getVendorRate(), config.getTotalTickets()));
                    vendorThreads[i].start();
                }

                // start customer threads
                Thread[] customerThreads = new Thread[config.getNumCustomers()];
                for (int i = 0; i < config.getNumCustomers(); i++) {
                    customerThreads[i] = new Thread(new CustomerService(ticketPool, i + 1, config.getCustomerRate()));
                    customerThreads[i].start();
                }

                // wait for all threads to finish
                while (VendorService.ticketCounter <= config.getTotalTickets() || ticketPool.getAvailableTickets() > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Main thread interrupted");
                    }
                }

                System.out.println("All tickets have been sold." );

                System.out.print("Enter 1 to exit or any other key to continue: ");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if (input.equals("1")) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("Restarting the simulation...");
                    interruptThreads(vendorThreads);
                    interruptThreads(customerThreads);
                }

            }
        };
    }

    private static void interruptThreads(Thread[] threads) {
        for (Thread thread : threads) {
            if (thread != null && thread.isAlive()) {
                thread.interrupt();
            }
        }
    }

}
