package org.marco.chat.cliente;

import org.marco.chat.utilidades.FechaUtil;
import java.io.*;

public class HistorialCliente {
    private String ruta;

    public HistorialCliente() {
        // lo guardamos en descargas pa encontrarlo rapido
        String descargas = System.getProperty("user.home") + File.separator + "Downloads";
        File carpeta = new File(descargas, "ConversacionesChat");
        // si no existe la carpeta la creamos
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
        this.ruta = carpeta.getAbsolutePath();
    }

    // metodo pa guardar los mensajes en un txt
    public void guardarMensaje(String de, String para, String texto) {
        String archivo;
        // comparamos los nombres pa que el archivo siempre se llame igual
        // sin importar quien mande el mensaje
        if (de.compareTo(para) < 0) {
            archivo = de + "-" + para + ".txt";
        } else {
            archivo = para + "-" + de + ".txt";
        }
        File f = new File(ruta, archivo);
        // abrimos el archivo pa escribir al final sin borrar lo de antes
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f, true))) {
            String linea = "[" + FechaUtil.getFechaActual() + "] " + de + ": " + texto;
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}