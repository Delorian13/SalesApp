package com.salesapp.main;

import com.salesapp.service.CustomerService;
import com.salesapp.logging.SalesAppLogger;

import java.util.logging.Logger;

public class SalesAppMain {
    public static void main(String[] args) {
        Logger log = SalesAppLogger.getLogger();
        CustomerService customerService = new CustomerService();

        log.info("Sales Application started.");
        customerService.addCustomer("John Doe");
        customerService.addCustomer(""); // Triggers a warning
        log.info("Sales Application ended.");
    }
}

