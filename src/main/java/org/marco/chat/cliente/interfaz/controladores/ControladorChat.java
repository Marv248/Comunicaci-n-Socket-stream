package org.marco.chat.cliente.interfaz.controladores;

import org.marco.chat.cliente.HistorialCliente;

public class ControladorChat {

    private HistorialCliente h;

    public ControladorChat() {
        h = new HistorialCliente();
    }

    // funcion pa mandar los mensajes
    public void enviarMensaje(String yo, String para, String texto) {
        // Si hay alguien seleccionado es privado
        if (para != null && !para.equals("TODOS")) {
            ControladorConexion.enviarMensaje("@" + para + " " + texto);

            // guardamos local para tener copia de lo que mandamos
            h.guardarMensaje(yo, para, texto);
        } else {
            // si no, va para todos
            ControladorConexion.enviarMensaje(texto);
            h.guardarMensaje(yo, "General", texto);
        }
    }

    // para cuando nos llega un mensaje de otro
    public void guardarMensajeRecibido(String de, String para, String msg) {
        h.guardarMensaje(de, para, msg);
    }
}