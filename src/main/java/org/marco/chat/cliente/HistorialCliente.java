package org.marco.chat.cliente;

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
        // 1. Obtener la ruta de la carpeta de Descargas del usuario actual
        String rutaDescargas = System.getProperty("user.home") + File.separator + "Downloads";
        // 2. Definir la nueva carpeta y el archivo
        File nuevaCarpeta = new File(rutaDescargas, "ConversacionesGuardadas");
        File archivo = new File(nuevaCarpeta, "conversacion.txt");
        try {
            // 3. Crear la carpeta si no existe
            if (!nuevaCarpeta.exists()) {
                if (nuevaCarpeta.mkdirs()) {
                    System.out.println("Carpeta creada con éxito.");
                }
            }
            // 4. Escribir el archivo dentro de la carpeta creada
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
}
