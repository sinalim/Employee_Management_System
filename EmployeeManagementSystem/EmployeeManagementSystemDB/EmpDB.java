package EmployeeManagementSystemDB;

import java.util.Properties;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

public class EmpDB {
    public static Connection getConnection() {
        try {
            Properties props = new Properties();
            // This loads your config.properties file
            props.load(new FileInputStream("src/resources/config.properties"));

            return DriverManager.getConnection(
                    props.getProperty("db.url"),
                    props.getProperty("db.user"),
                    props.getProperty("db.password")
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


