package org.example;


public class Main {
    public static void main(String[] args) {

        new Thread(() ->{
            Server servidor = new Server();
            servidor.main(new String[0]);
        }).start();

   Login login = new Login();
   login.setVisible(true);

    }
}



