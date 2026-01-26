package org.marco.chat.modelo;

import java.time.LocalDateTime;

public class Mensaje {
    private String contenido;
    private Usuario emisor;
    private Usuario receptor;
    private LocalDateTime fecha;

    public Mensaje(String contenido, Usuario emisor, Usuario receptor, LocalDateTime fecha) {
        this.contenido = contenido;
        this.emisor = emisor;
        this.receptor = receptor;
        this.fecha = fecha;
    }

    public String getContenido() {
        return contenido;
    }
    public Usuario getEmisor() {
        return emisor;
    }
    public Usuario getReceptor() {
        return receptor;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
}
