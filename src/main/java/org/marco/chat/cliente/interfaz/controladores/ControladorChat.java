package org.marco.chat.cliente.interfaz.controladores;

import org.marco.chat.cliente.HistorialCliente;
import org.marco.chat.utilidades.FechaUtil;

import java.util.List;

///     ControladorChat
/// Clase encargada de comunicar a la interfaz (vista) de Swing con el servidor.

public class ControladorChat {

    private HistorialCliente historial;

    public ControladorChat() {
        historial = new HistorialCliente();
    }

    public List<String> cargarHistorial() {
        return historial.cargar();
    }

    public void guardar(String mensaje) {
        historial.agregarMensaje(mensaje);
    }

    public void enviarMensaje(String mensaje) {
        String registro = "[" + FechaUtil.getFechaActual() + "] Yo: "  + mensaje;
        System.out.println(registro); // Copia del registro en la consola

        // ENVIA MENSAJE AL SERVER
        ControladorConexion.enviarMensaje(mensaje);

        // GUARDA EL MENSAJE DEL LADO DEL CLIENTE
        historial.agregarMensaje(mensaje);
    }

    public void guardarMensajeNuevo(String mensaje) {
        String registro = "[" + FechaUtil.getFechaActual() + "] "  + mensaje;
        historial.agregarMensaje(registro);
    }
}
