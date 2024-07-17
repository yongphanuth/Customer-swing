package com.fine;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        JDBCconnection JDBCconnection = null;
        Customer customer = null;

        try {
            // Initialize the database connection
            JDBCconnection = new JDBCconnection();
            // Create and display the Customer GUI
            customer = new Customer(JDBCconnection.getResultSet());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (JDBCconnection != null) {
                // Close the database connection when done
                JDBCconnection.close();
            }
        }
    }
}
