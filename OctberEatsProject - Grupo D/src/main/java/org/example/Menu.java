package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class  Menu extends JFrame {

    private JButton restaurantsButton;
    private JButton productsButton;
    private JButton usuariosButton;
    private JPanel MenuPanel;
    private JPanel centralPanel;
    private JButton órdenesButton;
    private JLabel logo;
    private JButton inicioButton;
    private JButton restaurantesBttn;
    private JPanel restBttnPanel;
    private JButton productsBttn;
    private JPanel productsBttnPanel;
    private JPanel footer;
    private JLabel footerText;
    private JLabel menuWelcomeTitle;
    private JPanel sideMenu;


    public Menu() {
        setContentPane(MenuPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1920, 1080);


        menuWelcomeTitle.setText("¡Te damos la bienvenida, " );


        restaurantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestaurantV2 rest = new RestaurantV2();
                rest.setVisible(true);
                dispose();
            }
        });

        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductItemv2 product = new ProductItemv2();
                product.setVisible(true);
                dispose();
            }
        });
    }
}
