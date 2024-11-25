package org.example;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Restaurant {
    private int Id;
    private String RestaurantName;
    private String Address;
    private String Schedule;
    private String Rating;

    public Restaurant() {
        DBConextion db = new DBConextion();
        db.StablishConection();
        Connection conn = db.StablishConection();
    }

    public Restaurant(String restaurantName, String address, String schedule, String rating) {
        String query = "INSERT INTO Octobereatsdb.Restaurant(RestaurantName,Address,Schedulee,Rating)VALUES(?,?,?,?);";


        try {
            DBConextion db = new DBConextion();
            db.StablishConection();
            Connection conn = db.StablishConection();

            this.RestaurantName = restaurantName;
            this.Address = address;
            this.Schedule = schedule;
            this.Rating = rating;

            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, restaurantName);
                preparedStatement.setString(2, address);
                preparedStatement.setString(3, schedule);
                preparedStatement.setString(4, rating);


                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Success Register");

                }
            } else {
                JOptionPane.showMessageDialog(null, "Cannot established a connection with the data base.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error data insert: " + ex.getMessage());
        }

    }

    public int getId() {
        return this.Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getRestaurantName() {
        return this.RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.RestaurantName = restaurantName;
    }

    public String getAddress() {
        return this.Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getSchedule() {
        return this.Schedule;
    }

    public void setSchedule(String schedule) {
        this.Schedule = schedule;
    }

    public String getRating() {
        return this.Rating;
    }

    public void setRating(String rating) {
        this.Rating = rating;
    }

    public void ReceiveNewOrders() {
    }

    public void UpdateMenu() {
    }

    public void UpdateAvailability() {
    }


}
