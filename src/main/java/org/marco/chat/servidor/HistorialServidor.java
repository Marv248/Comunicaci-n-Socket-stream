package org.marco.chat.servidor;

import org.marco.chat.utilidades.FechaUtil;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialServidor {
    private final List<String> listaLog = new ArrayList<>();
    private final File carpeta;

    public HistorialServidor() {
        // lo guardamos en descargas
        String rutaBase = System.getProperty("user.home") + File.separator + "Downloads";
        this.carpeta = new File(rutaBase, "HistorialServidor");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        cargarTodo();
    }

    // metodo pa guardar los chats privados entre dos personas
    public void guardarMensaje(String de, String para, String texto) {
        // ordenamos los nombres alfabeticamente pa que el archivo sea unico
        String par = (de.compareTo(para) < 0) ? de + "-" + para : para + "-" + de;
        // creamos el archivo dentro de nuestra carpeta de historial
        File fChat = new File(carpeta, par + ".txt");
        try (PrintWriter out = new PrintWriter(new FileWriter(fChat, true))) {
            String fecha = FechaUtil.getFechaActual();
            out.println("[" + fecha + "] " + de + ": " + texto);
        } catch (IOException e) {
            System.err.println("error al escribir el txt: " + e.getMessage());
        }
    }

    // registro global de lo que pasa en el server
    public synchronized void agregarMensaje(String msg) {
        String linea = "[" + FechaUtil.getFechaActual() + "] " + msg;
        listaLog.add(linea);
        File log = new File(carpeta, "log_maestro.txt");
        escribir(log, linea);
    }

    // metodo pa escribir rapido en cualquier archivo
    private void escribir(File f, String texto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f, true))) {
            bw.write(texto);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("fallo el guardado: " + e.getMessage());
        }
    }

    // cargamos el log maestro por si se ocupa ver despues
    public synchronized List<String> cargarTodo() {
        listaLog.clear();
        File log = new File(carpeta, "log_maestro.txt");
        if (!log.exists()) return listaLog;
        try (BufferedReader br = new BufferedReader(new FileReader(log))) {
            String s;
            while ((s = br.readLine()) != null) {
                listaLog.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaLog;
    }

    public synchronized List<String> getMensajes() {
        return new ArrayList<>(listaLog);
    }
}