package org.example.Mysql;

import org.example.DBConextion;
import org.example.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RestaurantV2 extends JFrame {
    private JPanel RestaurantPanel;
    private JTable Restaurants;
    private JButton createButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JTextField TxtName;
    private JTextField TxtAddress;
    private JTextField TxtSchedule;
    private JTextField TxtRating;
    private JLabel RestNameLabel;
    private JLabel RestAddressLabel;
    private JLabel RestScheduleLabel;
    private JLabel RestNameRatingLabel;
    private JLabel CategoryLabel;
    private JLabel PhoneLabel;
    private JLabel EmailLabel;
    private JTextField TxtPhone;
    private JTextField TxtEmail;
    private JTextField TxtCategory;



    public RestaurantV2() {
        setContentPane(RestaurantPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600, 600);


        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateRestaurant();
            }
        });
    }


    private void CreateRestaurant(){

        String Name = TxtName.getText();
        String Address = TxtAddress.getText();
        String Schedule = TxtSchedule.getText();
        String Rating = TxtRating.getText();
        String Category = TxtCategory.getText();
        String Phone = TxtPhone.getText();
        String Email = TxtEmail.getText();


        String Consult ="INSERT INTO OctoberEatsDB.Restaurants(RestaurantName,Address,RestaurantSchedule,Rating,Category,Phone,Email) VALUES(?,?,?,?,?,?,?);";

        try {

            DBConextion db = new DBConextion();
            Connection conn = db.StablishConection();

            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement(Consult);
                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, Address);
                preparedStatement.setString(3, Schedule);
                preparedStatement.setString(4, Rating);
                preparedStatement.setString(5, Category);
                preparedStatement.setString(6, Phone);
                preparedStatement.setString(7,Email);



                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Restaurant Created");
                    TxtName.setText("");
                    TxtAddress.setText("");
                    TxtSchedule.setText("");
                    TxtRating.setText("");
                    TxtCategory.setText("");
                    TxtPhone.setText("");
                    TxtEmail.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cannot established a connection with the data base.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error data insert: " + ex.getMessage());
        }
    }

    private void DeleteRestaurant(){

    }
    private void UpdateRestaurant(){

    }


}

