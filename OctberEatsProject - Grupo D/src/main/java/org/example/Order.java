package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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
    private JButton inicioButton;
    private JPanel orderPanel;
    private JPanel centralPanel;
    private JLabel restTitle;
    private JButton regresarButton;

    public Order() {
        setContentPane(orderPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1920, 1080);

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.setVisible(true);
                dispose();
            }
        });

        Createbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createOrder();
            }
        });

        editOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editOrder();
            }
        });

        deleteOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteOrder();
            }
        });

        updateOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateOrder();
            }
        });

       addNewItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewItems();
            }
        });

        trackOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trackOrder();
            }
        });

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

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.toString());
        }
    }

    public void editOrder() {

        int selectedRow = DataJTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) DataJTable.getModel();
        int id = (int) model.getValueAt(selectedRow, 0);
        String OrderNumber = TxtOrderNumber.getText();
        String Date = TxtDate.getText();
        String Time =  TxtTime.getText();
        String DeliveryTime =  TxtDeliveryTime.getText();
        String User =  TxtUser.getText();
        String ProductItem = TxtProductItem.getText();
        int age = Integer.parseInt(IdTxt.getText());


        String sql = "UPDATE OctoberEatsDB.Usersv2 SET UserName = ?, Password = ?, Firstname = ?, LastName = ?, Phone = ?, email = ?, Address = ?, Age = ? WHERE ID = ?";
        DBConextion con = null;

        try {
            con = new DBConextion();
            Connection connection = con.StablishConection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, TxtOrderNumber.getText());
            stmt.setString(2, TxtDate.getText());
            stmt.setString(3, TxtTime.getText());
            stmt.setString(4, TxtDeliveryTime.getText());
            stmt.setString(5, TxtUser.getText());
            stmt.setString(6, TxtProductItem.getText());
            stmt.setInt(7, Integer.parseInt(IdTxt.getText()));
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                model.setValueAt(OrderNumber, selectedRow, 1);
                model.setValueAt(Date, selectedRow, 2);
                model.setValueAt(Time, selectedRow, 3);
                model.setValueAt(DeliveryTime, selectedRow, 4);
                model.setValueAt(User, selectedRow, 5);
                model.setValueAt(ProductItem, selectedRow, 6);

                JOptionPane.showMessageDialog(null, "Fila actualizada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar la fila.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar la fila en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    public void deleteOrder() {
        try {
            DBConextion connection = new DBConextion();
            String query = "DELETE FROM OctoberEatsDB.Orders WHERE Id = ?";
            PreparedStatement ps = connection.StablishConection().prepareStatement(query);

            ps.setInt(1, Integer.parseInt(IdTxt.getText()));
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Order Deleted Successfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.toString());
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

    public void updateOrder() {
        editOrder();
    }
}
