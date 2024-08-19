package com.surveyproject.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    final private String URL = "jdbc:mysql://localhost:3306/farmacy";
    final private String USER = "root";
    final private String PASSWORD = "1048065293=)";

    public DatabaseConnection() {}

    public Connection connectDatabase(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
