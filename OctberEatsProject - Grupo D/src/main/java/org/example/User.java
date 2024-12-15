package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });

        regresarButton.addActionListener(new ActionListener() {
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

        inicioMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.setVisible(true);
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

        RefreshListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RefreshTable();
            }
        });
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword();
            }
        });

        updateUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });
        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteUser();
            }
        });
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

    private void createOrder() {
        //Crea una orden y pasa a la ventana de Order
        Order orderWindow = new Order();
        orderWindow.setVisible(true);
        dispose() ;



        /*  try {
            JOptionPane.showMessageDialog(null, "Order Created!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.toString());
        }*/
    }

    private void trackOrder() {
        //Le da seguimiento a una orden y pasa a la ventana de Order
        Order orderWindow = new Order();
        orderWindow.setVisible(true);
        dispose();
        // Lógica para rastrear orden
        /* try {

            JOptionPane.showMessageDialog(null, "Tracking Order...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.toString());
        }*/
    }

    private void updateUser() {
        int selectedRow = DataJTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) DataJTable.getModel();
        int id = (int) model.getValueAt(selectedRow, 0);

        String UserName = TxtUsername.getText();
        String Password = TxtPassword.getText();;
        String FirstName = TxtFirstname.getText();
        String LastName =  TxtLastName.getText();
        String phone = TxtPhone.getText();
        String email = TxtEmail.getText();
        int age = Integer.parseInt(TxtAge.getText());
        String address = TxtAddress.getText();

        String sql = "UPDATE OctoberEatsDB.Usersv2 SET UserName = ?, Password = ?, Firstname = ?, LastName = ?, Phone = ?, email = ?, Address = ?, Age = ? WHERE ID = ?";
        DBConextion con = null;

        try {
            con = new DBConextion();
            Connection connection = con.StablishConection();
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, TxtUsername.getText());
            stmt.setString(2, TxtPassword.getText());
            stmt.setString(3, TxtFirstname.getText());
            stmt.setString(4, TxtLastName.getText());
            stmt.setString(5, TxtPhone.getText());
            stmt.setString(6, TxtEmail.getText());
            stmt.setInt(7, Integer.parseInt(TxtAge.getText()));
            stmt.setString(8, TxtAddress.getText());
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                model.setValueAt(UserName, selectedRow, 1);
                model.setValueAt(Password, selectedRow, 2);
                model.setValueAt(FirstName, selectedRow, 3);
                model.setValueAt(LastName, selectedRow, 4);
                model.setValueAt(phone, selectedRow, 5);
                model.setValueAt(email, selectedRow, 6);
                model.setValueAt(email, selectedRow, 7);
                model.setValueAt(address, selectedRow, 8);

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
    public void RefreshTable() {
        String sql = "SELECT * FROM OctoberEatsDB.Users;";
        DBConextion con = null;
        ResultSet resultado = null;

        try {

            con = new DBConextion();
            resultado = con.getResult(sql);

            DefaultTableModel md = new DefaultTableModel();
            md.setColumnIdentifiers(new Object[]{"UserName", "Password", "Firstname", "LastName", "Phone", "email", "Address", "Age"});

            while (resultado.next()) {
                md.addRow(new Object[]{
                        resultado.getInt("UserName"),
                        resultado.getString("Firstname"),
                        resultado.getString("LastName"),
                        resultado.getString("Phone"),
                        resultado.getString("email"),
                        resultado.getString("Address"),
                        resultado.getString("Age"),

                });
            }
            DataJTable.setModel(md);

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
    public void DeleteUser() {

        int selectedRow = DataJTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) DataJTable.getModel();
        int id = (int) model.getValueAt(selectedRow, 0);


        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar esta fila?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM OctoberEatsDB.Userv2 WHERE Id = ?";
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
}
