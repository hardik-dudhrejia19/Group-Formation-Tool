package com.advancesd.group17.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_17_DEVINT?useSSL=false";
    private static final String USER = "CSCI5308_17_DEVINT_USER";
    private static final String PASSWORD = "CSCI5308_17_DEVINT_17284";
    private static DatabaseConfig dbInstance;
    private Connection connection;

    private DatabaseConfig() throws SQLException {
        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Exception " + ex.getMessage());
        }
    }

    public static DatabaseConfig getInstance() throws SQLException {
        if (dbInstance == null || dbInstance.getConnection().isClosed()) {
            dbInstance = new DatabaseConfig();
        }
        return dbInstance;
    }

    public Connection getConnection() {
        return connection;
    }
}
