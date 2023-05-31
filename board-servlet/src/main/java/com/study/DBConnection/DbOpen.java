package com.study.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 */
public class DbOpen {
    private static Connection conn;
    public static final String dbUrl = "jdbc:mysql://localhost:3306/ebrainsoft_study";
    public static final String dbId = "ebsoft";
    public static final String dbPassword = "ebsoft";

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbUrl, dbId, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
