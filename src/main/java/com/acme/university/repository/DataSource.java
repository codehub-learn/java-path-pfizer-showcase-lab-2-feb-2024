package com.acme.university.repository;

import com.acme.university.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.System.exit;

public class DataSource {

    private static final Logger log = LoggerFactory.getLogger(DataSource.class);
    private static final String DB_CONNECTION_URL_MEMORY_MODE = "jdbc:h2:mem:university";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // no longer needed, source:
            // https://stackoverflow.com/questions/39116196/why-need-for-using-jdbc-write-class-forname-for-each-connection
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(DB_CONNECTION_URL_MEMORY_MODE, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            log.error("Error while retrieving database connection.", e);
            exit(-1);
        }
        return connection;
    }

}
