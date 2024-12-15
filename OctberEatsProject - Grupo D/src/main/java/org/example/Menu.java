package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class  Menu extends JFrame {

    private JButton restaurantsButton;
    private JButton productsButton;
    private JPanel MenuPanel;
    private JButton ordersButton;
    private JButton inicioMenu;
    private JButton restaurantsMenu;
    private JPanel restBttnPanel;
    private JButton productsBttn;
    private JPanel productsBttnPanel;
    private JPanel footer;
    private JLabel footerText;
    private JLabel menuWelcomeTitle;
    private JPanel sideMenu;
    private JLabel logo;
    private JPanel newCentralPanel;
    private JButton usuariosButton;
    private JButton ordersMenu;
    private JButton productosMenu;
    private JButton usersMenu;


    public Menu() {
        setContentPane(MenuPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1920, 1080);


        menuWelcomeTitle.setText("¡Te damos la bienvenida!" );

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

        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setVisible(true);
                dispose();
            }
        });


        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductItemv2 productItemv2 = new ProductItemv2();
                productItemv2.setVisible(true);
                dispose();
            }
        });

        ordersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order = new Order();
                order.setVisible(true);
                dispose();
            }
        });


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
