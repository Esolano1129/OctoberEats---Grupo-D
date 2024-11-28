package org.example;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Category {

        String name;
        String image;

        public Category() {
            DBConextion NewConextion = new DBConextion();
            NewConextion.StablishConection();
        }

        public Category(String name, String image) {
            DBConextion NewConextion = new DBConextion();
            NewConextion.StablishConection();
            this.name = name;
            this.image = image;
            String query = "INSERT INTO Octobereatsdb.Category(Namec,Image)VALUES(?,?);";


            try {
                DBConextion db = new DBConextion();
                db.StablishConection();
                Connection conn = db.StablishConection();

                this.name = name;
                this.image = image;

                if (conn != null) {
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, image);


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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void SelectCategory() {
        }
        public void EditCategory() {
        }
        public void DeleteCategory() {
        }
        public void CreateCategory() {
        }

}
