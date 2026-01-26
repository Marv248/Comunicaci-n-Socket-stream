package org.marco.chat.cliente;

import org.marco.chat.cliente.interfaz.paneles.PanelChat;
import javax.swing.SwingUtilities;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClienteReceptor extends Thread {
    private Socket s;
    private PanelChat pChat;

    public ClienteReceptor(Socket s, PanelChat pChat) {
        this.s = s;
        this.pChat = pChat;
    }

    @Override
    public void run() {
        // abrimos el lector pa recibir lo que mande el server
        try (BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
            String txt;
            // mientras estemos conectados y llegue texto
            while (!Thread.currentThread().isInterrupted() && (txt = in.readLine()) != null) {
                // si el server manda la lista de gente
                if (txt.startsWith("LIST_UPDATE:")) {
                    actualizarGente(txt);
                } else {
                    // si es un mensaje normal lo mandamos al area de chat
                    final String msg = txt;
                    SwingUtilities.invokeLater(() -> pChat.mostrarMensaje(msg));
                }
            }
        } catch (Exception e) {
            // si se corta la conexion avisamos en el chat
            if (!s.isClosed()) {
                SwingUtilities.invokeLater(() ->
                        pChat.mostrarMensaje("⚠️ SISTEMA: Te has desconectado."));
            }
        }
    }

    // metodo pa procesar los nombres de los conectados
    private void actualizarGente(String comando) {
        // cortamos el prefijo pa quedarnos con los nombres
        String datos = comando.length() > 12 ? comando.substring(12) : "";
        String[] users = datos.isEmpty() ? new String[0] : datos.split(",");
        SwingUtilities.invokeLater(() -> {
            String miNom = (pChat.getUsuarioActual() != null) ?
                    pChat.getUsuarioActual().getNombre() : null;
            // refrescamos la lista de la izquierda
            if (pChat.getPanelUsuarios() != null) {
                pChat.getPanelUsuarios().actualizarLista(users, miNom);
            }
        });
    }
}