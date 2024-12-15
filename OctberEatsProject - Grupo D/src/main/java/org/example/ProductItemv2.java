package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductItemv2 extends JFrame {
    private JTable ProductTable;
    private JButton createButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton refreshTableButton;
    private JButton clearButton;
    private JTextField TxtProductName;
    private JTextField TxtQuantity;
    private JTextField TxtPrice;
    private JTextField TxtDescription;
    private JTextField TxtDiscount;
    private JLabel ProductNameLabel;
    private JLabel QuantityLabel;
    private JLabel DescriptionLabel;
    private JPanel ProductPanel;
    private JTextField TxtRestaurant;
    private JButton BackButton;
    private JPanel sideMenu;
    private JLabel logo;
    private JButton inicioMenu;
    private JPanel centralPanel;
    private JLabel PriceLabel;
    private JLabel DiscountLabel;
    private JLabel RestaurantLabel;
    private JLabel titleLabel;
    private JButton regresarButton;
    private JScrollPane scrollPanel;
    private JButton ordersMenu;
    private JButton restaurantsMenu;
    private JButton productosMenu;
    private JButton usersMenu;


    public ProductItemv2() {
        setContentPane(ProductPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1920, 1080);

        createButton.setText("Añadir producto");

        inicioMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.setVisible(true);
                dispose();
            }
        });

        restaurantsMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestaurantV2 restaurantV2 = new RestaurantV2();
                restaurantV2.setVisible(true);
                dispose();
            }
        });

        usersMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setVisible(true);
                dispose();
            }
        });

        productosMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductItemv2 productItemv2 = new ProductItemv2();
                productItemv2.setVisible(true);
                dispose();
            }
        });

        ordersMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order = new Order();
                order.setVisible(true);
                dispose();
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateProduct();
            }
        });

        refreshTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RefreshTable();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteProduct();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateProduct();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TxtProductName.setText("");
                TxtQuantity.setText("");
                TxtPrice.setText("");
                TxtDescription.setText("");
                TxtDiscount.setText("");
                TxtRestaurant.setText("");
            }
        });

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.setVisible(true);
                dispose();
            }
        });


    }
    public void CreateProduct() {

        String ProductName = TxtProductName.getText();
        String Quantity = TxtQuantity.getText();
        String Price = TxtPrice.getText();
        String Description = TxtDescription.getText();
        String Discount = TxtDiscount.getText();
        String Restaurant = TxtRestaurant.getText();



        String query = "INSERT INTO OctoberEatsDB.ProductItems(ProductName,Quantity,Price,Descriptions,Discount,Restaurant) VALUES(?,?,?,?,?,?);";

        try {

            DBConextion db = new DBConextion();
            Connection conn = db.StablishConection();

            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, ProductName);
                preparedStatement.setString(2, Quantity);
                preparedStatement.setString(3, Price);
                preparedStatement.setString(4, Description);
                preparedStatement.setString(5, Discount);
                preparedStatement.setString(6, Restaurant);



                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Product Created");

                    TxtProductName.setText("");
                    TxtQuantity.setText("");
                    TxtPrice.setText("");
                    TxtDescription.setText("");
                    TxtDiscount.setText("");
                    TxtRestaurant.setText("");

                }
            } else {
                JOptionPane.showMessageDialog(null, "Cannot established a connection with the data base.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error data insert: " + ex.getMessage());
        }

    }
    public void RefreshTable() {
        String sql = "SELECT * FROM OctoberEatsDB.ProductItems;";
        DBConextion con = null;
        ResultSet resultado = null;

        try {

            con = new DBConextion();
            resultado = con.getResult(sql);

            DefaultTableModel md = new DefaultTableModel();
            md.setColumnIdentifiers(new Object[]{"Id", "Product Name", "Quantity", "Price", "Description", "Discount", "Restaurant"});

            while (resultado.next()) {
                md.addRow(new Object[]{
                        resultado.getInt("Id"),
                        resultado.getString("ProductName"),
                        resultado.getString("Quantity"),
                        resultado.getString("Price"),
                        resultado.getString("Descriptions"),
                        resultado.getString("Discount"),
                        resultado.getString("Restaurant"),

                });
            }
            ProductTable.setModel(md);

        } catch (SQLException e) {
            System.err.println("Error al refrescar la tabla: " + e.getMessage());
            e.printStackTrace();
        } finally {

            try {
                if (resultado != null) resultado.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
    public void DeleteProduct() {

        int selectedRow = ProductTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) ProductTable.getModel();
        int id = (int) model.getValueAt(selectedRow, 0);


        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar esta fila?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM OctoberEatsDB.ProductItems WHERE Id = ?";
        DBConextion con = null;

        try {
            con = new DBConextion();
            Connection connection = con.StablishConection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {

                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Fila eliminada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar la fila.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar la fila de la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    private void UpdateProduct() {

        int selectedRow = ProductTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) ProductTable.getModel();
        int id = (int) model.getValueAt(selectedRow, 0);

        String name = TxtProductName.getText();
        String quantity = TxtQuantity.getText();
        String price = TxtPrice.getText();
        String description = TxtDescription.getText();
        String discount = TxtDiscount.getText();
        String restaurant = TxtRestaurant.getText();


        String sql = "UPDATE OctoberEatsDB.ProductItems SET ProductName = ?, Quantity = ?, Price = ?, Descriptions = ?, Discount = ?, Restaurant = ? WHERE Id = ?";
        DBConextion con = null;

        try {
            con = new DBConextion();
            Connection connection = con.StablishConection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, quantity);
            stmt.setString(3, price);
            stmt.setString(4, description);
            stmt.setString(5, discount);
            stmt.setString(6, restaurant);
            stmt.setInt(7, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {

                model.setValueAt(name, selectedRow, 1);
                model.setValueAt(quantity, selectedRow, 2);
                model.setValueAt(price, selectedRow, 3);
                model.setValueAt(description, selectedRow, 4);
                model.setValueAt(discount, selectedRow, 5);
                model.setValueAt(restaurant, selectedRow, 6);


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

}
