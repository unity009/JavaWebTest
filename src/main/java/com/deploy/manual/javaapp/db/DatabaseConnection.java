package com.deploy.manual.javaapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletResponse;

public class DatabaseConnection {
	
    public static Connection getConnection() throws SQLException {
    	
    	
    	 // Set the relative path to the keystore
        String keystorePath = "src/main/resources/JavaKeyStore.jks"; // Adjust path if needed
        String keystorePassword = "test1234";

        
        // Convert relative path to absolute path
        String absoluteKeystorePath = new java.io.File(keystorePath).getAbsolutePath();

        
        
        String host = "sqlserver-test.c7gk24g8cp3u.ap-south-1.rds.amazonaws.com";   // RDS instance endpoint
        String port = "1433";                 // Default MSSQL port
        String dbName = "TEST"; // Database name
        String username = "sa";  // SQL Server username
        String password = "mindmill";  // SQL Server password

        // Set system properties for the keystore
        System.setProperty("javax.net.ssl.keyStore", absoluteKeystorePath);
        System.setProperty("javax.net.ssl.keyStorePassword", keystorePassword);
        System.setProperty("javax.net.ssl.trustStore", absoluteKeystorePath);
        System.setProperty("javax.net.ssl.trustStorePassword", keystorePassword);
        
        
        String url = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + dbName+";encrypt=true";
        //for security
       // String url = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + dbName+";encrypt=true";
        //jdbc:sqlserver://<server_name>:<port>;databaseName=<database_name>;encrypt=true;trustServerCertificate=false;trustStore=<path_to_truststore>;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found", e);
        }
    }
    
    public static void main(String args[])
    {
    	   Connection conn = null;
           Statement stmt = null;
           ResultSet rs = null;
           
           try {
               conn = DatabaseConnection.getConnection();
               stmt = conn.createStatement();
               String query = "SELECT * FROM Students";  // Example query to fetch students
               rs = stmt.executeQuery(query);
               if(rs.next())
               {
            	   String id=rs.getString(1);
            	   String name=rs.getString(2);
            	   System.out.println("id:"+id);
            	   System.out.println("name:"+name);
               }
               
               
           } catch (SQLException e) {
               e.printStackTrace();
               
           } finally {
               // Close resources
               try {
                   if (rs != null) rs.close();
                   if (stmt != null) stmt.close();
                   if (conn != null) conn.close();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
    }

}
