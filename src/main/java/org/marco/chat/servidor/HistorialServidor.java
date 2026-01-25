package org.marco.chat.servidor;

import org.marco.chat.utilidades.FechaUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialServidor {

    private final List<String> mensajes = new ArrayList<>();
    private final File archivo;

    public HistorialServidor() {
        String rutaBase = System.getProperty("user.home") + File.separator + "Downloads";
        // Carpeta del servidor
        File carpeta = new File(rutaBase, "HistorialServidor");

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        archivo = new File(carpeta, "historial_servidor.txt");
        // Cargar historial previo si existe
        cargar();
    }

    // ================================
    // AGREGA MENSAJE
    // ================================
    public synchronized void agregarMensaje(String mensaje) {
        String registro = FechaUtil.getFechaActual() + " | " + mensaje; // TODO: AGREGAR EL NOMBRE DEL USUARIO
        mensajes.add(registro);
        guardarEnArchivo(registro);
    }

    // ================================
    // GUARDA EN ARCHIVO
    // ================================
    private synchronized void guardarEnArchivo(String mensaje) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(mensaje);
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================================
    // CARGAR HISTORIAL
    // ================================
    public synchronized List<String> cargar() {
        mensajes.clear();

        if (!archivo.exists()) {
            return mensajes;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                mensajes.add(linea);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return mensajes;
    }

    // ================================
    // OBTENER HISTORIAL
    // ================================
    public synchronized List<String> getMensajes() {
        return new ArrayList<>(mensajes);
    }
}
