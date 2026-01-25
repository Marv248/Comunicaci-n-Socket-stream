package org.marco.chat.cliente.interfaz.controladores;

import org.marco.chat.cliente.ClienteReceptor;

import javax.swing.*;
import java.io.PrintWriter;
import java.net.Socket;

public class ControladorConexion  {

    private static Socket socket;
    private static PrintWriter salida;
    private static ClienteReceptor receptor;

    private ControladorConexion() {
        // Evita instanciación
    }

    public static boolean conectar(String ip, int puerto, String nombreUsuario) {
        try {
            socket = new Socket(ip, puerto);

            salida = new PrintWriter(socket.getOutputStream(), true);

            // IMPRIME COMO PRIMERa LÍNEA EL NOMBRE DEL USUARIO PARA EL SERVIDOR
            salida.println(nombreUsuario);

            // HILO DEL RECEPTOR
            receptor = new ClienteReceptor(socket);
            receptor.start();

            System.out.println("Conectado al servidor " + ip + ":" + puerto);
            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "No se pudo conectar con el servidor.\n" +
                            "Verifique que el servidor esté activo y los datos sean correctos.\n\n" +
                            "Detalle: " + e.getMessage(),
                    "Error de conexión",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    public static void enviarMensaje(String mensaje) {
        if (salida != null) {
            salida.println(mensaje);
        }
    }

    public static Socket getSocket() {
        return socket;
    }

    public static void cerrarConexion() {
        try {
            if (receptor != null) receptor.interrupt();
            if (socket != null) socket.close();
        } catch (Exception ignored) {}
    }
}