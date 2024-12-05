package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PUERTO = 5432;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor escuchando en el puerto " + PUERTO);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                new Thread(() -> manejarCliente(socket)).start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    private static void manejarCliente(Socket socket) {
        try (
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream())
        ) {
            String operacion = (String) entrada.readObject();
            RestaurantV2 restaurant = new RestaurantV2();

            switch (operacion) {
                case "CREATE":
                    String[] datosCrear = (String[]) entrada.readObject();
                    restaurant.CreateRestaurant(datosCrear);
                    salida.writeObject("OK");
                    break;

                case "REFRESH":
                    salida.writeObject("REFRESH INVOCADO EN EL SERVIDOR");
                    break;

                case "DELETE":
                    salida.writeObject("DELETE NO IMPLEMENTADO AÚN");
                    break;

                case "UPDATE":
                    salida.writeObject("UPDATE NO IMPLEMENTADO AÚN");
                    break;

                default:
                    salida.writeObject("OPERACIÓN NO VÁLIDA");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al manejar cliente: " + e.getMessage());
        }
    }

}
