package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RestaurantV2 extends JFrame {


    private JLabel RestNameLabel;
    private JLabel AddressLabel;
    private JLabel ScheduleLabel;
    private JLabel RatingLabel;
    private JTextField TxtRestaurantName;
    private JTextField TxtAddress;
    private JTextField TxtSchedule;
    private JTextField TxtRating;
    private JTextField TxtCategory;
    private JTextField TxtPhone;
    private JTextField TxtEmail;
    private JButton Createbutton;
    private JButton DeleteButton;
    private JButton UpdateButton;
    private JPanel RestPanel;
    private JLabel logo;
    private JPanel sideMenu;
    private JButton inicioButton;
    private JLabel footerText;
    private JPanel footer;
    private JPanel centralPanel;
    private JButton RefreshListButton;
    private JLabel CategoryLabel;
    private JLabel PhoneLabel;
    private JLabel EmailLabel;
    private JLabel restTitle;
    private JButton regresarButton;
    private JTable DataJTable;
    private JScrollPane scrollPanel;
    private JButton logout;


    public RestaurantV2(){
        setContentPane(RestPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1920, 1080);


        Createbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateRestaurant();
            }
        });

        inicioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.setVisible(true);
                dispose();
            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
                dispose();
            }
        });

    }

    public void CreateRestaurant(){

        String RestaurantName = TxtRestaurantName.getText();
        String Address = TxtAddress.getText();
        String RestaurantSchedule = TxtSchedule.getText();
        String Rating = TxtRating.getText();
        String Category = TxtCategory.getText();
        String Phone = TxtPhone.getText();
        String Email = TxtEmail.getText();



        String query = "INSERT INTO OctoberEatsDB.Restaurants(RestaurantName,Address,RestaurantSchedule,Rating,Category,Phone,Email) VALUES(?,?,?,?,?,?,?);";

        try {
            // Conexión a la base de datos
            DBConextion db = new DBConextion();
            Connection conn = db.StablishConection();

            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, RestaurantName);
                preparedStatement.setString(2, Address);
                preparedStatement.setString(3, RestaurantSchedule);
                preparedStatement.setString(4, Rating);
                preparedStatement.setString(5, Category);
                preparedStatement.setString(6, Phone);
                preparedStatement.setString(7, Email);


                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Restaurant Created");

                    TxtRestaurantName.setText("");
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

}
