package org.example.Controller;

import org.example.Utilities.DBConextion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register extends JFrame {
    private JPanel RegisterPanel=null;
    private JLabel FNLabel;
    private JLabel LNLabel;
    private JLabel UsernameLabel;
    private JLabel PassLabel;
    private JLabel PhoneLabel;
    private JLabel EmailLabel;
    private JLabel AgeLabel;
    private JLabel AddressLabel;
    private JButton registerButton;
    private JTextField TxtFN;
    private JTextField TxtLN;
    private JTextField TxtUserName;
    private JTextField TxtPhone;
    private JTextField TxtEmail;
    private JTextField TxtAge;
    private JTextField TxtAddress;
    private JTextField TxtPassword;
    private JPanel centralPanel;
    private JButton backButton;

    public Register() {
        setContentPane(RegisterPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1920, 1080);


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToDatabase();

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
                dispose();
            }
        });

    }


    private void saveToDatabase() {

        String firstName = TxtFN.getText();
        String lastName = TxtLN.getText();
        String username = TxtUserName.getText();
        String password = TxtPassword.getText();
        String phone = TxtPhone.getText();
        String email = TxtEmail.getText();
        int age;
        String address = TxtAddress.getText();

        try {
            age = Integer.parseInt(TxtAge.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Incorrect Data Type: This field Should be a number");
            return;
        }


        String query = "INSERT INTO Octobereatsdb.Usersv2(Usernames,Passwords,FirstName,LastName,Phone,Email,Age,Address)VALUES(?,?,?,?,?,?,?,?);";

        try {
            // Conexión a la base de datos
            DBConextion db = new DBConextion();
            Connection conn = db.StablishConection();

            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, firstName);
                preparedStatement.setString(4, lastName);
                preparedStatement.setString(5, phone);
                preparedStatement.setString(6, email);
                preparedStatement.setInt(7, age);
                preparedStatement.setString(8, address);


                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Success Register");
                    Login login = new Login();
                    login.setVisible(true);
                    dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cannot established a connection with the data base.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error data insert: " + ex.getMessage());
        }
    }
}