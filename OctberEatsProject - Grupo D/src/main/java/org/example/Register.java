package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Register extends JFrame {
    private JPanel RegisterPanel;
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
    private JTable usersTable;
    private JScrollPane tableScrollPane;
    private JButton refreshButton;

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

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshUserTable();
            }
        });

        // Load initial data into the table
        refreshUserTable();
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
            JOptionPane.showMessageDialog(null, "Incorrect Data Type: Age should be a number");
            return;
        }

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() ||
                phone.isEmpty() || email.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return;
        }

        String query = "INSERT INTO Octobereatsdb.Usersv2(Usernames, Passwords, FirstName, LastName, Phone, Email, Age, Address) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try {
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
                    JOptionPane.showMessageDialog(null, "User registered successfully");
                    refreshUserTable(); // Update the table after adding a user
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cannot establish a connection with the database");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inserting data: " + ex.getMessage());
        }
    }

    private void refreshUserTable() {
        String sql = "SELECT * FROM Octobereatsdb.Usersv2;";

        try {
            DBConextion db = new DBConextion();
            ResultSet resultSet = db.getResult(sql);

            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new Object[]{"Id", "Username", "FirstName", "LastName", "Phone", "Email", "Age", "Address"});

            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getInt("Id"),
                        resultSet.getString("Usernames"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email"),
                        resultSet.getInt("Age"),
                        resultSet.getString("Address")
                });
            }

            usersTable.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading user data: " + e.getMessage());
        }
    }
}
