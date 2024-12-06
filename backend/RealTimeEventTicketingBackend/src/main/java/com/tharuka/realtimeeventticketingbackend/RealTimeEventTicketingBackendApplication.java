package com.tharuka.realtimeeventticketingbackend;

//import com.tharuka.realtimeeventticketingbackend.model.TicketPool;
//import com.tharuka.realtimeeventticketingbackend.service.VendorService;
//import com.tharuka.realtimeeventticketingbackend.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RealTimeEventTicketingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealTimeEventTicketingBackendApplication.class, args);
        System.out.println("Real-Time Event Ticketing System is running...");


//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            // Prompt the user for input
//            System.out.println("Enter total tickets: ");
//            int totalTickets = scanner.nextInt();
//
//            System.out.println("Enter maximum ticket capacity: ");
//            int maxCapacity = scanner.nextInt();
//
//            System.out.println("Enter number of vendors: ");
//            int numVendors = scanner.nextInt();
//
//            System.out.println("Enter vendor ticket release rate (tickets/second): ");
//            int vendorRate = scanner.nextInt();
//
//            System.out.println("Enter number of customers: ");
//            int numCustomers = scanner.nextInt();
//
//            System.out.println("Enter customer ticket purchase rate (tickets/second): ");
//            int customerRate = scanner.nextInt();
//
//            TicketPool ticketPool = new TicketPool(maxCapacity);
//
//            // Create the ticket pool and start the services
//            Thread[] vendorThreads = new Thread[numVendors]; // Array to hold vendor threads
//            for (int i = 0; i < numVendors; i++) {
//                vendorThreads[i] = new Thread(new VendorService(ticketPool, i + 1, vendorRate, totalTickets));
//                vendorThreads[i].start();
//            }
//
//            Thread[] customerThreads = new Thread[numCustomers]; // Array to hold customer threads
//            for (int i = 0; i < numCustomers; i++) {
//                customerThreads[i] = new Thread(new CustomerService(ticketPool, i + 1, customerRate));
//                customerThreads[i].start();
//            }
//
//            // Wait for all threads to finish
//            while (true){
//                if (VendorService.ticketCounter > totalTickets && ticketPool.getAvailableTickets() == 0 ) {
//                    System.out.println("All tickets have been sold!");
//                    break;
//                }
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//
//            }
//
//            System.out.println("Enter 1 to continue or 0 to exit: ");
//            if (scanner.nextInt() == 0) {
//                System.out.println("Exiting...");
//                break;
//            }
//
//        }
    }

}
