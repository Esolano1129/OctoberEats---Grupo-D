package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame{
    private JLabel PassLabel;
    private JLabel UsernameLabel;
    private JTextField UsernameTxt;
    private JPasswordField PasswordJPassField;
    private JButton LoginButton;
    private JButton RegisterButton;
    private JPanel MainPanel;


    public Login(){
        setContentPane(MainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500,500);

        DBConextion NewConextion = new DBConextion();
        NewConextion.StablishConection();

        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validaUsuario(UsernameTxt, PasswordJPassField);
            }
        });

    }
    public void validaUsuario(JTextField usernameTxt, JPasswordField PasswordJPassField) {

        try {
            ResultSet rs = null;
            PreparedStatement ps = null;

            DBConextion conection = new DBConextion();

            String consult = "SELECT * FROM OctoberEatsDB.Usersv2 WHERE Usersv2.Usernames = (?) AND Usersv2.Passwords = (?);";
            ps = conection.StablishConection().prepareStatement(consult);

            String Pass = String.valueOf(PasswordJPassField.getPassword());

            ps.setString(1, UsernameTxt.getText());
            ps.setString(2, Pass);

            rs = ps.executeQuery();

            if (rs.next()) {

                JOptionPane.showMessageDialog(null, "Welcome to October Eats");
                dispose();
                Menu principalMenu = new Menu();
                principalMenu.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Access Denied: Wrong Username or Password");
            }


        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "ERROR: " + error.toString());
        }

      }

}