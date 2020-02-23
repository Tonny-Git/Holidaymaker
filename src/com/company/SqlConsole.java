package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SqlConsole {

    private Connection conn =  null;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private ResultSet currentCustomer = null;

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

            statement = conn.prepareStatement("SELECT id FROM customers WHERE email = ?");
            statement.setString(1, email);
            currentCustomer = statement.executeQuery();
        } catch (java.sql.SQLIntegrityConstraintViolationException e){
            try {
                statement = conn.prepareStatement("SELECT id FROM customers WHERE email = ?");
                statement.setString(1, email);
                currentCustomer = statement.executeQuery();
                System.out.println("User was fetched from the database");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //'Pool', 'Restaurant', 'Evening entertainment', 'Kids club'
    public void searchHotels(ArrayList<String> choices) {
        int count = 0;
        for (String choice : choices) {
            if (!choice.contains("")) {
                count++;
            }
        }
        String amount = count + "";
        try {
            statement = conn.prepareStatement("SELECT *, COUNT(h.id) >= ? AS boolean_value FROM hotels h JOIN hotels_x_hotel_informations hi ON hi.hotels_id = h.id JOIN hotel_informations i ON hi.hotel_informations_id = i.id WHERE information IN (?, ?, ?, ?) GROUP BY h.id ORDER BY h.id");
            statement.setString(1, amount);
            statement.setString(2, choices.get(0));
            statement.setString(3, choices.get(1));
            statement.setString(4, choices.get(2));
            statement.setString(5, choices.get(3));

            resultSet = statement.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    //Ta bort???
    public ArrayList<String> printHotelSearchResult() {
        ArrayList<String> hotelId = new ArrayList<>();

        try {
            while (resultSet.next()) {
                hotelId.add(resultSet.getString("hotels_id"));
                String row = "hotels_id: " + resultSet.getString("hotels_id") +
                        ", name: " + resultSet.getString("name") +
                        ", boolean_value: " + resultSet.getString("boolean_value") + ".";
                System.out.println(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelId;
    }

    public ArrayList<Integer> addHotelResultArray() {
        ArrayList<Integer> hotelId = new ArrayList<>();

        try {
            while (resultSet.next()) {
                hotelId.add(Integer.parseInt(resultSet.getString("hotels_id")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = hotelId.size(); i <= 4; i++) {
            hotelId.add(0);
        }

        return hotelId;
    }

    public void searchAfterRooms(String startDate, String endDate, String amountOfPeople) {
        ArrayList<Integer> hotels= addHotelResultArray();
        try {
            statement = conn.prepareStatement("SELECT r.id AS room_id, r.hotels_id, rd.id AS room_rent_id, h.name, hf.price, d.rent_start_date, d.rent_end_date, size FROM rooms r JOIN rooms_x_room_rent_dates rd ON r.id = rd.rooms_id JOIN hotels h ON h.id = r.hotels_id JOIN hotel_fees hf ON hf.id = r.hotels_fees_id JOIN room_rent_dates d ON d.id = rd.room_rent_dates_id JOIN total_size ts ON ts.total_size_id = r.hotels_id WHERE r.hotels_id IN (?, ?, ?, ?) AND is_available = 1 AND d.rent_start_date >= ? AND d.rent_end_date <= ? AND total_available_size >= ? GROUP BY size, room_rent_dates_id, hotels_id ORDER BY r.id, d.rent_start_date;");
            statement.setInt(1, hotels.get(0));
            statement.setInt(2, hotels.get(1));
            statement.setInt(3, hotels.get(2));
            statement.setInt(4, hotels.get(3));
            statement.setString(5, startDate);
            statement.setString(6, endDate);
            statement.setString(7, amountOfPeople);


            resultSet = statement.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> printRoomSearchResult() {
        ArrayList<String> hotelId = new ArrayList<>();
        int i = 0;
        try {
            while (resultSet.next()) {
                i++;
                hotelId.add(resultSet.getString("room_rent_id"));
                String row = //"hotels_id: " + resultSet.getString("hotels_id") +
                        "[" + i + "]Hotel name: " + resultSet.getString("name") +
                        ", Travel date: " + resultSet.getString("rent_start_date") +
                        ", Return date: " + resultSet.getString("rent_end_date") +
                        ", Room size: " + resultSet.getString("size") +
                        ", Room price: " + resultSet.getString("price") +".";
                System.out.println(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelId;
    }
}
