package com.surveyproject.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    final private String URL = "jdbc:mysql://localhost:3306/surveyCampus";
    final private String USER = "campus2023";
    final private String PASSWORD = "campus2023";

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
