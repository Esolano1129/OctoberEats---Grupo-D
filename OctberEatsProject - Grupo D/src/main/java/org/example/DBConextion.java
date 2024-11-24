package org.example;

import javax.swing.*;
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
}
