package org.marco.chat.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {
    private int port;
    private HistorialServidor historialServidor;

    public ServidorSocket(int port, HistorialServidor historialServidor) {
        this.port = port;
        this.historialServidor = historialServidor;
    }

    public void iniciar(){
        System.out.println("Servidor Socket escuchando en puerto: " + port);
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept(); // Espera escuchando hasta que alguien se conecte
                new ClienteHilo(socket, historialServidor).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
