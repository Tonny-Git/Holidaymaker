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

        // Test data, ta bort senare.
        //searchByFirstName("Erik");
        //printSearchResult();
        //searchByFirstName("En");
        //printSearchResult();
    }

    private void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/holidaymaker_db?user=root&password=&serverTimezone=UTC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test data, ta bort/ändra
    private void searchByFirstName(String firstname) {
        try {
            statement = conn.prepareStatement("SELECT * FROM customers WHERE first_name LIKE (?)");
            statement.setString(1, firstname);
            resultSet = statement.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test data, ta bort/ändra
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

    public void addCustomerToDataBase(String firstName, String lastName, String email) {
        try {
            statement = conn.prepareStatement("INSERT INTO customers(first_name, last_name, email) VALUES(?, ?, ?)");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //'Pool', 'Restaurant', 'Evening entertainment', 'Kids club'
    public void searchHotels(String firstname) {
        try {
            statement = conn.prepareStatement("SELECT *, COUNT(h.id) >= ? AS boolean_value FROM hotels h JOIN hotels_x_hotel_informations hi ON hi.hotels_id = h.id JOIN hotel_informations i ON hi.hotel_informations_id = i.id WHERE information IN (?, ?, ?, ?) GROUP BY h.id ORDER BY h.id");
            statement.setString(1, "2");
            statement.setString(2, "Pool");
            statement.setString(3, "Restaurant");
            statement.setString(4, "");
            statement.setString(5, "");

            resultSet = statement.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public   void printHotelSearchResult() {
        try {
            while (resultSet.next()) {
                String row = "hotels_id: " + resultSet.getString("hotels_id") +
                        ", name: " + resultSet.getString("name") +
                        ", boolean_value: " + resultSet.getString("boolean_value") + ".";
                System.out.println(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
