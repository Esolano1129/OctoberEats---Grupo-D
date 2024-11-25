package org.example;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductItem extends Category {
    private String name;
    private int quantity;
    private double price;
    private String description;
    private double discount;

    public ProductItem() {
    }

    public ProductItem(String name, int quantity, double price, String description, double discount) {

        String query = "INSERT INTO Octobereatsdb.ProductItem(NameP,Quantity,Price,DescriptionP,Discount)VALUES(?,?,?,?,?);";


        try {
            DBConextion db = new DBConextion();
            db.StablishConection();
            Connection conn = db.StablishConection();

            this.name = name;
            this.quantity = quantity;
            this.price = price;
            this.description = description;
            this.discount = discount;

            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, String.valueOf(quantity));
                preparedStatement.setString(3, String.valueOf(price));
                preparedStatement.setString(4, description);
                preparedStatement.setString(5, String.valueOf(discount));


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

    public ProductItem(String name, String image, String name1, int quantity, double price, String description, double discount) {
        super(name, image);
        String query = "INSERT INTO Octobereatsdb.ProductItem(NameP,Quantity,Price,DescriptionP,Discount)VALUES(?,?,?,?,?);";


        try {
            DBConextion db = new DBConextion();
            db.StablishConection();
            Connection conn = db.StablishConection();

            this.name = name1;
            this.quantity = quantity;
            this.price = price;
            this.description = description;
            this.discount = discount;

            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, String.valueOf(quantity));
                preparedStatement.setString(3, String.valueOf(price));
                preparedStatement.setString(4, description);
                preparedStatement.setString(5, String.valueOf(discount));


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void AddToOrder() {
    }

    public void AddNewProduct() {
    }

    public void EditProduct() {
    }

    public void DeleteProduct() {
    }

}
