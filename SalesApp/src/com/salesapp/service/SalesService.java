package com.salesapp.service;

import com.salesapp.Sale;
import com.salesapp.db.DB;
import com.salesapp.logging.SalesAppLogger;

import java.sql.*;
import java.util.logging.Logger;

public class SalesService {
    private static final Logger log = SalesAppLogger.getLogger();

    public void recordSale(Sale sale) {
        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO sales (customer, product, quantity, total) VALUES (?, ?, ?, ?)")) {

            stmt.setString(1, sale.getCustomer().getName());
            stmt.setString(2, sale.getProduct().getName());
            stmt.setInt(3, sale.getQuantity());
            stmt.setDouble(4, sale.getTotal());

            stmt.executeUpdate();
            log.info("Sale recorded: " + sale.getCustomer().getName() + " bought " +
                     sale.getQuantity() + " of " + sale.getProduct().getName() + " = $" + sale.getTotal());

        } catch (SQLException e) {
            log.severe("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void printReport() {
        try (Connection conn = DB.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM sales")) {

            System.out.println("\n=== Sales Report ===");
            boolean hasResults = false;

            while (rs.next()) {
                hasResults = true;
                System.out.println(rs.getInt("id") + ": " +
                    rs.getString("customer") + " bought " +
                    rs.getInt("quantity") + " x " +
                    rs.getString("product") + " = $" +
                    rs.getDouble("total"));
            }

            if (!hasResults) {
                System.out.println("No sales recorded yet.");
            }

        } catch (SQLException e) {
            log.severe("Failed to fetch sales report: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

