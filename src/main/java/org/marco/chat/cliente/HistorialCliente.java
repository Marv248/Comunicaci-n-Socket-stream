package org.marco.chat.cliente;

import java.util.ArrayList;
import java.util.List;

public class HistorialCliente {
    private List<String > mensajes = new ArrayList<>();

    public void agregarMensaje(String mensaje){
        mensajes.add(mensaje);
    }

    public void guardarMensajes(){
        // TODO: guardar los mensajes en un .txt
    }
}
