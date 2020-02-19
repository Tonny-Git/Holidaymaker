package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SqlConsole {

    private Connection conn =  null;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public SqlConsole() {
        connect();
        searchByFirstName("Erik");
        printSearchResult();
        searchByFirstName("En");
        printSearchResult();
    }

    private void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/holidaymaker_db?user=root&password=&serverTimezone=UTC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchByFirstName(String firstname) {
        try {
            statement = conn.prepareStatement("SELECT * FROM customers WHERE first_name LIKE (?)");
            statement.setString(1, firstname);
            resultSet = statement.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  void printSearchResult() {
        try {
            while (resultSet.next()) {
                String row = "id: " + resultSet.getString("id") +
                            ", name: " + resultSet.getString("first_name") + " " + resultSet.getString("last_name") +
                            ", email: " + resultSet.getString("email") + ".";
                System.out.println(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
