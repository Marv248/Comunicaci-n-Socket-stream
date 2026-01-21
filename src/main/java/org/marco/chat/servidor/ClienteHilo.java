package org.marco.chat.servidor;

import org.marco.chat.modelo.Usuario;
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
        ) {
            String ipEmisor = socket.getInetAddress().getHostAddress();
            int puertoEmisor = socket.getPort();

            String nombreUsuario = br.readLine(); // La primer linea que ingrese será el usuario
            Usuario emisor = new Usuario(nombreUsuario + puertoEmisor, ipEmisor, puertoEmisor);

            String contenido;
            while ((contenido = br.readLine()) != null) {
                String registro = "[" + FechaUtil.getFechaActual() + "] " + emisor.getNombre() + ": " + contenido;
                pw.println("Servidor recibió: " + contenido);
                historialServidor.agregarMensaje(registro);
            }
        } catch (Exception e) {
            System.err.println("Cliente desconectado: " + e.getMessage());
        } finally {
            try { socket.close(); } catch (Exception ex) {}
        }
    }
}