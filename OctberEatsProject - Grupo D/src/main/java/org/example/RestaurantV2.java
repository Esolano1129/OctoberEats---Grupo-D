package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RestaurantV2 extends JFrame {


    private JLabel RestNameLabel;
    private JLabel AddressLabel;
    private JLabel ScheduleLabel;
    private JLabel RatingLabel;
    private JTextField TxtRestaurantName;
    private JTextField TxtAddress;
    private JTextField TxtSchedule;
    private JTextField TxtRating;
    private JTextField TxtCategory;
    private JTextField TxtPhone;
    private JTextField TxtEmail;
    private JButton Createbutton;
    private JButton borrarButton;
    private JButton UpdateButton;
    private JPanel RestPanel;
    private JLabel footerText;
    private JPanel footer;
    private JPanel centralPanel;
    private JButton RefreshListButton;
    private JLabel CategoryLabel;
    private JLabel PhoneLabel;
    private JLabel EmailLabel;
    private JLabel restTitle;
    private JButton regresarButton;
    private JTable DataJTable;
    private JPanel sideMenu;
    private JLabel logo;
    private JButton inicioButton;
    private JScrollPane scrollPanel;
    private JButton clearButton;


    public RestaurantV2() {
        setContentPane(RestPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1920, 1080);


        Createbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] datos = {
                        TxtRestaurantName.getText(),
                        TxtAddress.getText(),
                        TxtSchedule.getText(),
                        TxtRating.getText(),
                        TxtCategory.getText(),
                        TxtPhone.getText(),
                        TxtEmail.getText()
                };
                //Usamos el swingworker para ejecutar el thread en segundo plano
                new SwingWorker<Object, Void>() {
                    @Override
                    protected Object doInBackground() throws Exception {

                        return enviarSolicitud("CREATE", datos);
                    }

                    @Override
                    protected void done() {
                        try {
                            Object respuesta = get();

                            if ("OK".equals(respuesta)) {
                                JOptionPane.showMessageDialog(null, "Restaurante creado exitosamente");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al crear el restaurante");
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error de comunicación con el servidor");
                        }
                    }
                }.execute();  //Ejecuta el swingworker
            }
        });
        RefreshListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RefreshTable();
            }
        });

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteRestaurant();
            }
        });

        UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateRestaurant();
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
      clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TxtRestaurantName.setText("");
                TxtAddress.setText("");
                TxtSchedule.setText("");
                TxtRating.setText("");
                TxtCategory.setText("");
                TxtPhone.setText("");
                TxtEmail.setText("");
            }
        });

    }

    public void CreateRestaurant(String[] datos) {
        if (datos == null || datos.length < 7) {
            JOptionPane.showMessageDialog(null, "Datos inválidos para crear restaurante.");
            return;
        }

        String RestaurantName = datos[0];
        String Address = datos[1];
        String RestaurantSchedule = datos[2];
        String Rating = datos[3];
        String Category = datos[4];
        String Phone = datos[5];
        String Email = datos[6];

        if (RestaurantName.isEmpty() || Address.isEmpty() || RestaurantSchedule.isEmpty() ||
                Rating.isEmpty() || Category.isEmpty() || Phone.isEmpty() || Email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llena todos los campos.");
            return;
        }

        String query = "INSERT INTO OctoberEatsDB.Restaurants(RestaurantName, Address, RestaurantSchedule, Rating, Category, Phone, Email) VALUES(?,?,?,?,?,?,?);";

        try {
            DBConextion db = new DBConextion();
            Connection conn = db.StablishConection();

            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, RestaurantName);
                preparedStatement.setString(2, Address);
                preparedStatement.setString(3, RestaurantSchedule);
                preparedStatement.setString(4, Rating);
                preparedStatement.setString(5, Category);
                preparedStatement.setString(6, Phone);
                preparedStatement.setString(7, Email);

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    //JOptionPane.showMessageDialog(null, "Restaurant Created");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cannot establish a connection with the database.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inserting data: " + ex.getMessage());
        }
    }

    public void RefreshTable() {
        String sql = "SELECT * FROM OctoberEatsDB.Restaurants;";
        DBConextion con = null;
        ResultSet resultado = null;

        try {

            con = new DBConextion();
            resultado = con.getResult(sql);

            DefaultTableModel md = new DefaultTableModel();
            md.setColumnIdentifiers(new Object[]{"Id", "Name", "Address", "Schedule", "Rating", "Category", "Phone", "Email"});

            while (resultado.next()) {
                md.addRow(new Object[]{
                        resultado.getInt("Id"),
                        resultado.getString("RestaurantName"),
                        resultado.getString("Address"),
                        resultado.getString("RestaurantSchedule"),
                        resultado.getString("Rating"),
                        resultado.getString("Category"),
                        resultado.getString("Phone"),
                        resultado.getString("Email")
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

    public void DeleteRestaurant() {

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

        String sql = "DELETE FROM OctoberEatsDB.Restaurants WHERE Id = ?";
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

    private void UpdateRestaurant() {

        int selectedRow = DataJTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) DataJTable.getModel();
        int id = (int) model.getValueAt(selectedRow, 0);

        String name = TxtRestaurantName.getText();
        String address = TxtAddress.getText();
        String schedule = TxtSchedule.getText();
        String rating = TxtRating.getText();
        String category = TxtCategory.getText();
        String phone = TxtPhone.getText();
        String email = TxtEmail.getText();

        String sql = "UPDATE OctoberEatsDB.Restaurants SET RestaurantName = ?, Address = ?, RestaurantSchedule = ?, Rating = ?, Category = ?, Phone = ?, Email = ? WHERE Id = ?";
        DBConextion con = null;

        try {
            con = new DBConextion();
            Connection connection = con.StablishConection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, schedule);
            stmt.setString(4, rating);
            stmt.setString(5, category);
            stmt.setString(6, phone);
            stmt.setString(7, email);
            stmt.setInt(8, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {

                model.setValueAt(name, selectedRow, 1);
                model.setValueAt(address, selectedRow, 2);
                model.setValueAt(schedule, selectedRow, 3);
                model.setValueAt(rating, selectedRow, 4);
                model.setValueAt(category, selectedRow, 5);
                model.setValueAt(phone, selectedRow, 6);
                model.setValueAt(email, selectedRow, 7);

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
    private Object enviarSolicitud(String operacion, Object datos) {
        try (Socket socket = new Socket("127.0.0.1", 5432);
             ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())) {

            salida.writeObject(operacion);
            if (datos != null) {
                salida.writeObject(datos);
            }

            return entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al comunicarse con el servidor: " + e.getMessage());
            return null;
        }
    }
}