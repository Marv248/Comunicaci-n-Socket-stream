package org.marco.chat.servidor;

import org.marco.chat.modelo.Mensaje;

import java.util.ArrayList;
import java.util.List;

public class HistorialServidor {
    private List<String> mensajes = new ArrayList<>();

    public synchronized void agregarMensaje(String  mensaje) {
        mensajes.add(mensaje);
    }

    public void guardarMensaje(){
        // TODO: Agregar el mensaje al archivo
    }
}
