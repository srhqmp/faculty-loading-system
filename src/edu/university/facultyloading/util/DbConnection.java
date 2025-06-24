package edu.university.facultyloading.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/dbfacultyloading";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    static {
        // static block - code belongs to the class. will automatically run when called.
        try {
            Class.forName(DRIVER); // loads the driver
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load JDBC Driver: " + e.getMessage());
        }
    }

    public Connection connect() throws SQLException {
        // Returns Connection object
        return DriverManager.getConnection(URL, USERNAME, PASSWORD); // Register the driver
    }
}
