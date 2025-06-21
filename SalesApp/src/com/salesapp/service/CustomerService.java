package com.salesapp.service;

import com.salesapp.logging.SalesAppLogger;
import com.salesapp.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CustomerService {
    private static final Logger log = SalesAppLogger.getLogger();

    public void addCustomer(String name) {
        if (name == null || name.trim().isEmpty()) {
            log.warning("Attempted to add customer with empty name.");
            return;
        }

        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO customers (name) VALUES (?)")) {
            stmt.setString(1, name);
            stmt.executeUpdate();
            log.info("Customer added: " + name);
        } catch (SQLException e) {
            log.severe("Database error: " + e.getMessage());
        }
    }
}



