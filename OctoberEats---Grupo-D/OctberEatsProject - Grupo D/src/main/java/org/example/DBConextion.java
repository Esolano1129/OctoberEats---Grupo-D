package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConextion {

    Connection conect;

    String url = "jdbc:mysql://localhost:3306/octobereatsdb";
    String username = "root";
    String password = "admin";

    public Connection StablishConection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conect = DriverManager.getConnection(url, username, password);


        } catch (SQLException | ClassNotFoundException error) {
            error.printStackTrace();
        }
        return conect;
    }

    public static class Categoria {
        String name;
        String image;

        public Categoria() {
        }

        public Categoria(String name, String image) {
            this.name = name;
            this.image = image;
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
}
