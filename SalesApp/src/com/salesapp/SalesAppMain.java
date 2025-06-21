package com.salesapp;

import com.salesapp.service.CustomerService;
import com.salesapp.service.SalesService;
import com.salesapp.logging.SalesAppLogger;

import java.util.Scanner;
import java.util.logging.Logger;

public class SalesAppMain {
    public static void main(String[] args) {
        Logger log = SalesAppLogger.getLogger();
        Scanner scanner = new Scanner(System.in);
        CustomerService customerService = new CustomerService();
        SalesService salesService = new SalesService();

        log.info("Sales Application started.");

        boolean running = true;

        while (running) {
            System.out.println("\n=== Sales App Menu ===");
            System.out.println("1. Add Customer");
            System.out.println("2. Create Sale");
            System.out.println("3. View Sales Report");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    customerService.addCustomer(customerName);
                    break;

                case "2":
                    System.out.print("Enter customer name: ");
                    String custName = scanner.nextLine();
                    Customer customer = new Customer(custName);

                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();

                    System.out.print("Enter product price: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    Product product = new Product(productName, price);

                    System.out.print("Enter quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    Sale sale = new Sale(customer, product, quantity);
                    salesService.recordSale(sale);
                    break;

                case "3":
                    salesService.printReport();
                    break;

                case "4":
                    running = false;
                    log.info("User exited application.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
        log.info("Sales Application ended.");
    }
}

