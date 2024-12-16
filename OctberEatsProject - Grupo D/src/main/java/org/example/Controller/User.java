package org.example.Controller;

import org.example.Utilities.DBConextion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends JFrame {
    private JTextField TxtID;
    private JTextField TxtUsername;
    private JPasswordField TxtPassword;
    private JTextField TxtFirstname;
    private JTextField TxtLastName;
    private JTextField TxtAddress;
    private JTextField TxtAge;
    private JTextField TxtPhone;
    private JTextField TxtEmail;
    private JButton changePasswordButton;
    private JButton updateUserButton;
    private JPanel MainPanel;
    private JButton regresarButton;
    private JTable DataJTable;
    private JScrollPane scrollPanel;
    private JButton clearButton;
    private JButton RefreshListButton;
    private JButton DeleteButton;
    private JPanel sideMenu;
    private JLabel logo;
    private JButton inicioMenu;
    private JButton ordersMenu;
    private JButton restaurantsMenu;
    private JButton productosMenu;
    private JButton usersMenu;
    private JPanel centralPanel;
    private JLabel userTitle;

    public User() {
        setContentPane(MainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1920, 1080);

        clearButton.addActionListener(e -> clearFields());

        regresarButton.addActionListener(e -> navigateTo(new Menu()));

        restaurantsMenu.addActionListener(e -> navigateTo(new RestaurantV2()));

        inicioMenu.addActionListener(e -> navigateTo(new Menu()));

        usersMenu.addActionListener(e -> navigateTo(new User()));

        productosMenu.addActionListener(e -> navigateTo(new ProductItemv2()));

        ordersMenu.addActionListener(e -> navigateTo(new Order()));

        RefreshListButton.addActionListener(e -> refreshTable());

        changePasswordButton.addActionListener(e -> changePassword());

        updateUserButton.addActionListener(e -> updateUser());

        DeleteButton.addActionListener(e -> deleteUser());
    }

    private void clearFields() {
        TxtID.setText("");
        TxtUsername.setText("");
        TxtPassword.setText("");
        TxtFirstname.setText("");
        TxtLastName.setText("");
        TxtAddress.setText("");
        TxtAge.setText("");
        TxtPhone.setText("");
        TxtEmail.setText("");
    }

    private void navigateTo(JFrame frame) {
        frame.setVisible(true);
        dispose();
    }

    private void changePassword() {
        try {
            String newPassword = JOptionPane.showInputDialog("Enter New Password:");
            if (newPassword == null || newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Password cannot be empty!");
                return;
            }

            DBConextion connection = new DBConextion();
            String query = "UPDATE OctoberEatsDB.Usersv2 SET Passwords = ? WHERE Usernames = ?";
            PreparedStatement ps = connection.StablishConection().prepareStatement(query);
            ps.setString(1, newPassword);
            ps.setString(2, TxtUsername.getText());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Password updated successfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.toString());
        }
    }

    private void updateUser() {
        int selectedRow = DataJTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) DataJTable.getModel();
        int id = (int) model.getValueAt(selectedRow, 0);

        String sql = "UPDATE OctoberEatsDB.Usersv2 SET Usernames = ?, Passwords = ?, Firstname = ?, LastName = ?, Phone = ?, Email = ?, Address = ?, Age = ? WHERE ID = ?";
        try {
            DBConextion db = new DBConextion();
            Connection connection = db.StablishConection();
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, TxtUsername.getText());
            stmt.setString(2, TxtPassword.getText());
            stmt.setString(3, TxtFirstname.getText());
            stmt.setString(4, TxtLastName.getText());
            stmt.setString(5, TxtPhone.getText());
            stmt.setString(6, TxtEmail.getText());
            stmt.setString(7, TxtAddress.getText());
            stmt.setInt(8, Integer.parseInt(TxtAge.getText()));
            stmt.setInt(9, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "User updated successfully.");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(null, "Update failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTable() {
        String sql = "SELECT * FROM OctoberEatsDB.Usersv2;";

        try {
            DBConextion db = new DBConextion();
            ResultSet resultSet = db.getResult(sql);

            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new Object[]{"Id", "Username", "FirstName", "LastName", "Phone", "Email", "Address", "Age"});

            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getInt("Id"),
                        resultSet.getString("Usernames"),
                        resultSet.getString("Firstname"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email"),
                        resultSet.getString("Address"),
                        resultSet.getInt("Age")
                });
            }

            DataJTable.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading user data: " + e.getMessage());
        }
    }

    private void deleteUser() {
        int selectedRow = DataJTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) DataJTable.getModel();
        int id = (int) model.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this user?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM OctoberEatsDB.Usersv2 WHERE Id = ?";
        try {
            DBConextion db = new DBConextion();
            Connection connection = db.StablishConection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "User deleted successfully.");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(null, "Deletion failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
