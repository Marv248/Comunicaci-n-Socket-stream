package org.marco.chat.servidor;

import org.marco.chat.utilidades.FechaUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteHilo extends Thread{
    private Socket socket;
    private HistorialServidor historialServidor;

    public ClienteHilo(Socket socket, HistorialServidor historialServidor) {
        this.socket = socket;
        this.historialServidor = historialServidor;
    }

    @Override
    public void run() {
        try (
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
        ){
            String mensaje;
            while((mensaje = br.readLine()) != null){ //Asigna el texto del mensaje a "mensaje"
                String registro = "[" + FechaUtil.getFechaActual() +"] " + mensaje;
                pw.println(registro); // Se imprime también en la consola
                historialServidor.agregarMensaje(registro); // Se guardan todos los datos del mensaje en el servidor

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
