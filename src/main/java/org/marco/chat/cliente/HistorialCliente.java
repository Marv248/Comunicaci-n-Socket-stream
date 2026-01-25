package org.marco.chat.cliente;

import org.marco.chat.modelo.Mensaje;
import org.marco.chat.utilidades.FechaUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialCliente {
    private List<String > mensajes = new ArrayList<>();
    private File archivo;
    private int contador = 0;

    public void agregarMensaje(String mensaje){
        mensajes.add(FechaUtil.getFechaActual() + mensaje);
        guardarEnArchivo(FechaUtil.getFechaActual() + " |" + mensaje);
    }

    public HistorialCliente() {
        // CREA RUTA PARA LAS DESCARGAS
        String rutaDescargas = System.getProperty("user.home") + File.separator + "Downloads";
        // UBICACIÓN Y NOMBRE DEL ARCHIVO
        File nuevaCarpeta = new File(rutaDescargas, "ConversacionesGuardadas");

        if (!nuevaCarpeta.exists()) {
            nuevaCarpeta.mkdir();
        }

        archivo = new File(nuevaCarpeta, "conversacionesGuardadas" +  contador +".txt");
        contador++;
    }

    //TODO @CRISTO HAZ QUE LEA LOS DOCS Y QUE CARGUE EN EL ARRAYLIST LOS MENSAJES
    public List<String> cargar() {
        mensajes.clear();
        if (!archivo.exists()) {
            return mensajes;
        }
        try (BufferedReader br = new BufferedReader( new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                mensajes.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensajes;
    }

    private void guardarEnArchivo(String mensaje) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(mensaje);
            bw.newLine(); // Salto de línea
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getMensajes() {
        return mensajes;
    }
}
