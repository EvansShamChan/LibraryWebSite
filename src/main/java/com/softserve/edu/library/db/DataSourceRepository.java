package com.softserve.edu.library.db;

import java.sql.Driver;
import java.sql.SQLException;

public final class DataSourceRepository {
    private final static String FAILED_JDBC_DRIVER = "Failed to Create JDBC Driver";

    private DataSourceRepository() {
    }

    public static DataSource getDefault() {
        return getMySqlLocalHost();
    }

    public static DataSource getMySqlLocalHost() {
        Driver sqlDriver;
        try {
            sqlDriver = new com.mysql.jdbc.Driver();
        } catch (SQLException e) {
            // TODO Develop Custom Exceptions
            throw new RuntimeException(FAILED_JDBC_DRIVER);
        }
        return new DataSource(sqlDriver, "jdbc:mysql://localhost:3306/library", "khrystyna", "123456");
//                "jdbc:mysql://localhost:3306/library", "root", "root");
    }
}