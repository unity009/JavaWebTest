package com.deploy.manual.javaapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
    public static Connection getConnection() throws SQLException {
        String host = "sqlserver-test.c7gk24g8cp3u.ap-south-1.rds.amazonaws.com";   // RDS instance endpoint
        String port = "1433";                 // Default MSSQL port
        String dbName = "TEST"; // Database name
        String username = "sa";  // SQL Server username
        String password = "mindmill";  // SQL Server password

        String url = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + dbName+";encrypt=false";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found", e);
        }
    }

}
