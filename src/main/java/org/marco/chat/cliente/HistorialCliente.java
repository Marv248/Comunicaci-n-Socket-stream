package org.marco.chat.cliente;

import org.marco.chat.modelo.Mensaje;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistorialCliente {
    private List<String > mensajes = new ArrayList<>();

    public void agregarMensaje(String mensaje){
        mensajes.add(mensaje);
    }

    public void gerDoc() {
        // CREA RUTA PARA LAS DESCARGAS
        String rutaDescargas = System.getProperty("user.home") + File.separator + "Downloads";
        // UBICACIÓN Y NOMBRE DEL ARCHIVO
        File nuevaCarpeta = new File(rutaDescargas, "ConversacionesGuardadas");
        File archivo = new File(nuevaCarpeta, "conversacion.txt");
        try {
            // CREAR CARPETA
            if (!nuevaCarpeta.exists()) {
                if (nuevaCarpeta.mkdirs()) {
                    System.out.println("Carpeta creada con éxito.");
                }
            }
            // AGREGAR MENSAJES AL ARCHIVO
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                for (int i = 0; i < mensajes.size(); i++) {
                    bw.write(mensajes.get(i));
                    bw.newLine();
                }
                System.out.println("Archivo guardado en: " + archivo.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Error al manejar el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //TODO @CRISTO HAZ QUE LEA LOS DOCS Y QUE CARGUE EN EL ARRAYLIST LOS MENSAJES
    public List<String> cargar(){
        ArrayList<String> mensajes = new ArrayList<>();
        return mensajes;
    }
}
