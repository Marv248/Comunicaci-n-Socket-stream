package org.marco.chat.cliente.interfaz.controladores;

import org.marco.chat.cliente.ClienteReceptor;
import org.marco.chat.cliente.interfaz.paneles.PanelChat;
import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class ControladorConexion {

    // variables pa manejar la conexión del programa
    private static Socket s;
    private static PrintWriter out;
    private static ClienteReceptor rec;

    private ControladorConexion() {}

    public static boolean conectar(String ip, int p, String user, PanelChat panel) {
        try {
            s = new Socket(ip, p);
            out = new PrintWriter(s.getOutputStream(), true);
            // pa leer la respuesta del server rápido
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            // mandamos el nombre para ver si nos deja entrar
            out.println(user);
            // leemos si el server dijo OK o dio error
            String res = in.readLine();
            if (res != null && res.equals("OK")) {
                // si esta bien, prendemos el hilo que recibe mensajes
                rec = new ClienteReceptor(s, panel);
                rec.start();
                System.out.println("Login exitoso: " + user);
                return true;
            } else {
                // si el nombre ya estaba o algo pasó
                String error = (res != null) ? res : "El nombre ya esta en uso";
                JOptionPane.showMessageDialog(null, error, "Error de Usuario", JOptionPane.WARNING_MESSAGE);

                // cerramos lo que se abrio
                if (out != null) out.close();
                if (s != null) s.close();
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No hay server: " + e.getMessage());
            return false;
        }
    }

    // metodo simple para mandar texto al server
    public static void enviarMensaje(String m) {
        if (out != null) {
            out.println(m);
        }
    }

    // para cerrar cuando salimos
    public static void cerrarConexion() {
        try {
            if (out != null) out.close();
            if (rec != null) rec.interrupt();
            if (s != null) s.close();
            System.out.println("Desconectado.");
        } catch (Exception e) {
            // error al cerrar
        } finally {
            System.exit(0);
        }
    }
}