package org.marco.chat.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {
    private int port;
    private HistorialServidor historialServidor; //todo: podríamos agregar por cada conversación un archivo con el nombre los usuarios. Ejemplo: User1_User2.txt

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
