package org.postgresql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Db {
    private static final String URL = "jdbc:postgresql://localhost:2710/CharitySystem";
    private static final String USER = "postgres";
    private static final String PASS = "123";
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

