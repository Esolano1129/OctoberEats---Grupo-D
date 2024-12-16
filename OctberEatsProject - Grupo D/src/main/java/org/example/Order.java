package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Order extends JFrame {
    private JTextField IdTxt;
    private JTextField TxtOrderNumber;
    private JTextField TxtDate;
    private JTextField TxtTime;
    private JTextField TxtDeliveryTime;
    private JTextField TxtUser;
    private JTextField TxtProductItem;
    private JButton editOrderButton;
    private JButton deleteOrderButton;
    private JButton addNewItemsButton;
    private JButton trackOrderButton;
    private JButton Createbutton;
    private JButton updateOrderButton;
    private JPanel MainPanel;
    private JTable DataJTable;
    private JPanel sideMenu;
    private JLabel logo;
    private JButton inicioMenu;
    private JPanel orderPanel;
    private JButton regresarButton;
    private JButton ordersMenu;
    private JButton restaurantsMenu;
    private JButton productosMenu;
    private JButton usersMenu;
    private JPanel centralPanel;
    private JLabel restTitle;
    private JButton RefreshListButton;

    public Order() {
        setContentPane(orderPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1920, 1080);

        regresarButton.addActionListener(e -> navigateTo(new Menu()));

        inicioMenu.addActionListener(e -> navigateTo(new Menu()));

        restaurantsMenu.addActionListener(e -> navigateTo(new RestaurantV2()));

        productosMenu.addActionListener(e -> navigateTo(new ProductItemv2()));

        usersMenu.addActionListener(e -> navigateTo(new User()));

        ordersMenu.addActionListener(e -> navigateTo(new Order()));

        Createbutton.addActionListener(e -> createOrder());

        editOrderButton.addActionListener(e -> editOrder());

        deleteOrderButton.addActionListener(e -> deleteOrder());

        updateOrderButton.addActionListener(e -> editOrder());

        addNewItemsButton.addActionListener(e -> addNewItems());

        trackOrderButton.addActionListener(e -> trackOrder());
    }

    private void navigateTo(JFrame frame) {
        frame.setVisible(true);
        dispose();
    }

    public void createOrder() {
        try {
            DBConextion connection = new DBConextion();
            String query = "INSERT INTO OctoberEatsDB.Orders (OrderNumber, Date, Time, DeliveryTime, User, ProductItem) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.StablishConection().prepareStatement(query);

            ps.setString(1, TxtOrderNumber.getText());
            ps.setString(2, TxtDate.getText());
            ps.setString(3, TxtTime.getText());
            ps.setString(4, TxtDeliveryTime.getText());
            ps.setString(5, TxtUser.getText());
            ps.setString(6, TxtProductItem.getText());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Order Created Successfully!");
            refreshTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.toString());
        }
    }

    public void editOrder() {
        int selectedRow = DataJTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) DataJTable.getModel();
        int id = (int) model.getValueAt(selectedRow, 0);

        String sql = "UPDATE OctoberEatsDB.Orders SET OrderNumber = ?, Date = ?, Time = ?, DeliveryTime = ?, User = ?, ProductItem = ? WHERE Id = ?";
        try {
            DBConextion db = new DBConextion();
            Connection connection = db.StablishConection();
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, TxtOrderNumber.getText());
            stmt.setString(2, TxtDate.getText());
            stmt.setString(3, TxtTime.getText());
            stmt.setString(4, TxtDeliveryTime.getText());
            stmt.setString(5, TxtUser.getText());
            stmt.setString(6, TxtProductItem.getText());
            stmt.setInt(7, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Order updated successfully.");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(null, "Update failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteOrder() {
        int selectedRow = DataJTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) DataJTable.getModel();
        int id = (int) model.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this order?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM OctoberEatsDB.Orders WHERE Id = ?";
        try {
            DBConextion db = new DBConextion();
            Connection connection = db.StablishConection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Order deleted successfully.");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(null, "Deletion failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addNewItems() {
        try {
            String newItem = JOptionPane.showInputDialog("Enter New Product Item:");
            if (newItem != null && !newItem.isEmpty()) {
                TxtProductItem.setText(TxtProductItem.getText() + ", " + newItem);
                JOptionPane.showMessageDialog(null, "Item Added!");
            } else {
                JOptionPane.showMessageDialog(null, "No item added!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.toString());
        }
    }

    public void trackOrder() {
        try {
            DBConextion connection = new DBConextion();
            String query = "SELECT * FROM OctoberEatsDB.Orders WHERE OrderNumber = ?";
            PreparedStatement ps = connection.StablishConection().prepareStatement(query);

            ps.setString(1, TxtOrderNumber.getText());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Order Found! Delivery Time: " + rs.getString("DeliveryTime"));
            } else {
                JOptionPane.showMessageDialog(null, "Order Not Found!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.toString());
        }
    }

    private void refreshTable() {
        String sql = "SELECT * FROM OctoberEatsDB.Orders;";

        try {
            DBConextion db = new DBConextion();
            ResultSet resultSet = db.getResult(sql);

            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new Object[]{"Id", "OrderNumber", "Date", "Time", "DeliveryTime", "User", "ProductItem"});

            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getInt("Id"),
                        resultSet.getString("OrderNumber"),
                        resultSet.getString("Date"),
                        resultSet.getString("Time"),
                        resultSet.getString("DeliveryTime"),
                        resultSet.getString("User"),
                        resultSet.getString("ProductItem")
                });
            }

            DataJTable.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading order data: " + e.getMessage());
        }
    }
}
